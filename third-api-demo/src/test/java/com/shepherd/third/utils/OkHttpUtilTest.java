package com.shepherd.third.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shepherd.third.dto.Brand;
import com.shepherd.third.global.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/6 11:17
 */
@Slf4j
public class OkHttpUtilTest {

    private static final String url = "http://127.0.0.1:18200/api/mall/product/brand";

    @Test
    public void get() {
        Map<String, String> param = new HashMap<>();
        param.put("name", "小");
        String s = OkHttpUtil.get(url, param);
        ResponseVO responseVO = JSONObject.parseObject(s, new TypeReference<ResponseVO<List<Brand>>>(){});
        System.out.println(responseVO);

    }

    @Test
    public void post() {
    }

    @Test
    public void getForHeader() {
    }

    @Test
    public void postJsonParams() {
    }

    @Test
    public void postXmlParams() {
    }
}