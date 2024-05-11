package com.shepherd.basedemo.service.impl;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriteConfig;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/19 11:55
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CsvTest {


    @Test
    public void testExport() {
        CsvWriteConfig config = new CsvWriteConfig();
        config.setFieldSeparator('|');
        //指定路径和编码
        CsvWriter writer = CsvUtil.getWriter("/Users/shepherdmy/Desktop/testWrite.csv", CharsetUtil.CHARSET_UTF_8);
        //按行写出
        writer.write(
                new String[] {"张三", "18", "66.89"},
                new String[] {"李四", "27", "5686.45"},
                new String[] {"王五", "56", "234457"}
        );
        writer.close();

    }
}
