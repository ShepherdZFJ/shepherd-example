package com.shepherd.third.controller;

import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import com.shepherd.third.retrofit.RetrofitBrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/2 18:50
 */
@RestController
@RequestMapping("/retrofit")
public class RetrofitController {
    @Resource
    private RetrofitBrandService retrofitBrandService;
    @GetMapping("/brand")
    public ResponseVO<List<Brand>> getList() {
        ResponseVO<List<Brand>> responseVO = retrofitBrandService.getList(2l);
        return responseVO;

    }
}
