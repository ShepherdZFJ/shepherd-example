package com.shepherd.basedemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.shepherd.basedemo.excel.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/11 18:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ExcelTest {

    @Test
    public void testExcelRead() {
        String fileName = "/Users/shepherdmy/Desktop/testExcel.xlsx";
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }
}
