package com.shepherd.zk.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/10/9 14:21
 */
public class ZkClient {
    // 注意：逗号左右不能有空格
    // 连接集群 10.10.0.10:2181,10.10.0.22:2181,10.10.0.26:2181
    private String connectString = "10.10.0.10:2181";
    private int sessionTimeout = 10000;
    private ZooKeeper zkClient;

    /**
     * 初始化，创建zk客户端
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        // 创建客户端
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            // 指定默认监听器watcher
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 收到事件通知后的回调函数
                System.out.println("默认监听器：" + watchedEvent.toString());
                System.out.println("-------------------------------");
                //持续监听 注册一次只能监听一次，下面循环注册监听
                List<String> children = null;
                try {
                    // 再次注册监听，
                    children = zkClient.getChildren("/shepherd", true);

                    for (String child : children) {
                        System.out.println(child);
                    }

                    System.out.println("-------------------------------");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        // 参数1： 节点路径   参数2： 节点数据 参数3： 节点权限   参数4： 节点类型
        String nodeCreated = zkClient.create("/shepherd", "MeiYing".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取节点的子节点并注册监听，这里可以自己定义监听器watcher，但是注册一次，也只能监听一次，如下注释示例
     * 当然也可以通过zkClient.getChildren("/shepherd", true)实现，此时会执行创建zkClient时声明的默认监听器可实现持续监听，
     * 如上面初始化创建zkClient所示
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {

//        List<String> children = zkClient.getChildren("/shepherd", new Watcher() {
//            @Override
//            public void process(WatchedEvent watchedEvent) {
//                // 收到事件通知后的回调函数（用户的业务逻辑）
//                System.out.println("监听信息：" + watchedEvent.toString());
//            }
//        });
//        for (String child : children) {
//            System.out.println(child);
//        }

        List<String> children = zkClient.getChildren("/shepherd", true);

        // 延时 →watch为true，代表监听， 在zkClient的process()方法持续监听后续的节点变化
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 判断节点是否存储
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {

        // 客户端不启动监听
        Stat stat = zkClient.exists("/shepherd", false);
        System.out.println(stat == null? "not exist " : "exist");
    }
}
