package com.shepherd.common.controller;

import com.shepherd.common.entity.Brand;
import com.shepherd.common.service.BrandService;
import com.shepherd.common.vo.BrandVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/5/25 14:08
 */
@RestController
@RequestMapping("/v1/brand")
public class BrandController {
    @Resource
    private BrandService brandService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public void addBrand(@RequestBody @Validated BrandVO brandVO) {
        System.out.println(brandVO);
    }

//    @GetMapping
//    public List<Brand> getList() {
//        return brandService.getAll();
//    }

    @GetMapping("/redis")
    public String getRedisValue() {
        String value = stringRedisTemplate.opsForValue().get("brand_key");
        return value;
    }

}
