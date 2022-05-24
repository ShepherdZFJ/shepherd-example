package com.shepherd.common.limit.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 17:07
 */

/**
 * 这个是漏斗算法的单机实现版，当前漏斗相关数据都是在java内存Funnel实例中，并没有保存在redis，保存在redis用hash存即可，但是需要保证相关原子化
 * 和一致性
 */
public class FunnelRateLimiter {
    // 漏斗类
    static class Funnel {
        int capacity;  // 容量
        float leakingRate; // 速率
        int leftQuota; // 剩余容量
        long leakingTs; // 上次漏水时间
        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }
        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs; //当前时间和上次漏水时间查找
            int deltaQuota = (int) (deltaTs * leakingRate); // 这段时间漏掉的水容量
            if (deltaQuota < 0) { // 间隔时间太大，整数溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            if (deltaQuota < 1) { // 腾出的空间还不到最小单位1
                return;
            }
            this.leftQuota += deltaQuota; // 更新剩余容量空间
            this.leakingTs = nowTs; // 更新上次漏水时间
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }
        // 判断能不能加水，如果当前漏斗剩余容量leftQuota不足要加quota，就返回false拒绝加水
        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }
    private Map<String, Funnel> funnels = new HashMap<>();
    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1);
    }
}
