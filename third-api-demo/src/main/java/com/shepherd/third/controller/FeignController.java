package com.shepherd.third.controller;

import com.shepherd.third.dto.Brand;
import com.shepherd.third.feign.BrandService;
import com.shepherd.third.global.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/1 17:48
 */
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Resource
    private BrandService brandService;

    @GetMapping("/brand")
    public ResponseVO<List<Brand>> getList() {
        ResponseVO<List<Brand>> responseVO = brandService.getList(2l);
        return responseVO;

    }
}
