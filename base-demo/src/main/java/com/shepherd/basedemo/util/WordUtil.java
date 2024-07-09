package com.shepherd.basedemo.util;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/6/27 18:16
 */
public class WordUtil {

    public static void main(String[] args) throws IOException {
        //The core API uses a minimalist design, only one line of code is required
        String source = "/Users/shepherdmy/Desktop/test.docx";
        Map<String, String> map = new HashMap<>();
        map.put("应还本金", "张三");
        map.put("var2","18");
        map.put("var3", "2024-06-27");
        map.put("var4","188.809");
        map.put("var5", "sdfsdf2谁带饭");
//        XWPFTemplate.compile(source).render(map).writeToFile("out_template.docx");

        // 加载Word模板
        XWPFTemplate template = XWPFTemplate.compile(source);


        // 创建数据映射


        // 替换文档中的占位符
        template.render(map);

        // 输出替换后的文档
        template.writeAndClose(new FileOutputStream("/Users/shepherdmy/Desktop/document2.docx"));
    }
}
