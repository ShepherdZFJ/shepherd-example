package com.shepherd.third.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import com.shepherd.third.utils.HttpClientUtil;
import com.shepherd.third.utils.OkHttpUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/7 13:53
 */
@RestController
@RequestMapping("/httpclient")
public class HttpClientController {

    @GetMapping("/brand")
    public ResponseVO<List<Brand>> getList() {
        String url = "http://127.0.0.1:18200/api/mall/product/brand";
        Map<String, Object> param = new HashMap<>();
        param.put("name", "小");
        String s = HttpClientUtil.get(url, param);
        ResponseVO<List<Brand>> responseVO = JSONObject.parseObject(s, new TypeReference<ResponseVO<List<Brand>>>() {});
        return responseVO;
    }

    @PostMapping("/brand")
    public ResponseVO addBrand(@RequestBody Brand brand) {
        String url = "http://127.0.0.1:18200/api/mall/product/brand";
        Map<String, Object> rst = new HashMap<>();
        rst.put("categoryId", 1l);
        rst.put("name","apple苹果");
        rst.put("letter", "A");
        rst.put("description", "使用httpclient创建");
        rst.put("isDelete", 0);
        String s = HttpClientUtil.post(url, rst);
        ResponseVO responseVO = JSONObject.parseObject(s, ResponseVO.class);
        return responseVO;
    }
}
