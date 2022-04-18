package com.shepherd.example.transaction.lock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shepherd.example.entity.Brand;
import com.shepherd.example.transaction.lock.dao.BrandDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/18 22:38
 */
@Service
@Slf4j
public class BrandService {
    @Resource
    private BrandDAO brandDAO;

    public List<Brand> getAll() {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        List<Brand> brandList = brandDAO.selectList(queryWrapper);
        return brandList;
    }
}
