package com.shepherd.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shepherd.common.entity.Brand;
import com.shepherd.common.dao.BrandDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/18 22:38
 */
@Service
@Slf4j
public class BrandService {
//    @Resource
//    private BrandDAO brandDAO;
//
//    public List<Brand> getAll() {
//        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
//        List<Brand> brandList = brandDAO.selectList(queryWrapper);
//        return brandList;
//    }
//
//    // 普通条件更新，全表数据锁住
//    @Transactional(rollbackFor = Exception.class)
//    public void update() throws InterruptedException {
//        LambdaUpdateWrapper<Brand> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.ge(Brand::getCreateTime, "2021-02-03 00:00:00");
//        updateWrapper.set(Brand::getDescription, "更新描述");
//        brandDAO.update(new Brand(), updateWrapper);
//        // 睡眠1分钟，模仿长事务
//        TimeUnit.MINUTES.sleep(1);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void updateByPrimaryKey() throws InterruptedException {
//        Brand brand = new Brand();
//        brand.setId(1115L);
//        brand.setDescription("htc就是火腿肠手机啦");
//        brand.setUpdateTime(new Date());
//        brandDAO.updateById(brand);
//        // 睡眠1分钟，模仿长事务
//        TimeUnit.MINUTES.sleep(1);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void updateByIndex() throws InterruptedException {
//        LambdaUpdateWrapper<Brand> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(Brand::getLetter, "b");
//        updateWrapper.set(Brand::getDescription, "通过索引字段条件更新描述信息哦11111");
//        brandDAO.update(new Brand(), updateWrapper);
//        // 睡眠1分钟，模仿长事务
//        TimeUnit.MINUTES.sleep(1);
//    }
//
//
//    /**
//     * updateOne()和update()一起执行时，由于update()事务brand表数据锁住，所以updateOne会报错：
//     *  Lock wait timeout exceeded; try restarting transaction;
//     *
//     *  updateByPrimaryKey()和update()一起执行时, 即使updateByPrimaryKey()是长事务，也不会锁住，因为updateByPrimaryKey()事务
//     *  不会锁全表数据，只锁当前主键行数据
//     *
//     *  updateByIndex()update()一起执行时, 只会锁住命中索引的行，不会全表锁住
//     *
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void updateOne() {
//        Brand brand = new Brand();
//        brand.setId(1528l);
//        brand.setIsDelete(2);
//        brand.setUpdateTime(new Date());
//        brandDAO.updateById(brand);
//    }
}
