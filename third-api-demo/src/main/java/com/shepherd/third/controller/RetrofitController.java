package com.shepherd.third.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import com.shepherd.third.retrofit.RetrofitBrandService;
import com.shepherd.third.utils.OkHttpUtil;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/2 18:50
 */
@Slf4j
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

    @PostMapping("/brand")
    public ResponseVO addBrand(@RequestBody Brand brand) {
        ResponseVO responseVO = retrofitBrandService.addBrand(brand);
        return responseVO;
    }

    @GetMapping("/brand/okhttp")
    public ResponseVO<List<Brand>> getList2() {
        String url = "http://127.0.0.1:18200/api/mall/product/brand";
        Map<String, String> param = new HashMap<>();
        param.put("name", "小");
        String s = OkHttpUtil.get(url, param);
        ResponseVO<List<Brand>> responseVO = JSONObject.parseObject(s, new TypeReference<ResponseVO<List<Brand>>>() {});
        return responseVO;
    }

    @PostMapping("/brand/okhttp")
    public void addBrand2() {
        String url = "http://127.0.0.1:18200/api/mall/product/brand";
        JSONObject rst = new JSONObject();
        rst.put("categoryId", 1l);
        rst.put("name","apple苹果");
        rst.put("letter", "A");
        rst.put("description", "使用okhttp创建");
        rst.put("isDelete", 0);
        String s = OkHttpUtil.postJsonParams(url, rst.toJSONString());
        ResponseVO responseVO = JSONObject.parseObject(s, ResponseVO.class);
        log.info("responseVO: {}", responseVO);
    }
}
