package com.shepherd.example.transaction.lock.service;

import com.shepherd.example.entity.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/18 22:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BrandServiceTest {
    @Resource
    private BrandService brandService;

    @Test
    public void getAll() {
        List<Brand> list = brandService.getAll();
        System.out.println(list.size());
        System.out.println(list);
    }
}