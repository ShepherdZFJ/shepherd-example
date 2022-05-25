package com.shepherd.common.controller;

import com.shepherd.common.entity.Brand;
import com.shepherd.common.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<Brand> getList() {
        return brandService.getAll();
    }

}
