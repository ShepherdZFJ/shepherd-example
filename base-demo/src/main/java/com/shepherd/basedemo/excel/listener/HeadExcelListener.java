package com.shepherd.basedemo.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.shepherd.basedemo.entity.Account;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/22 16:18
 */
@Slf4j
public class HeadExcelListener extends AnalysisEventListener<Account> {
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {

        if (exception instanceof ExcelAnalysisException) {
            throw exception;
        }
        log.error("======>>>解析异常：", exception);
    }

    @Override
    public void invoke(Account data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("sheet={} 所有数据解析完成！", context.readSheetHolder().getSheetName());
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        // 处理表头数据
        for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
            Integer columnIndex = entry.getKey(); // 表头所在列的索引
            String columnName = entry.getValue(); // 表头的名称
            System.out.println("列索引：" + columnIndex + "，列名称：" + columnName);
        }
        // 抛出异常，停止解析数据
        throw new ExcelAnalysisException("表头读取完毕");
    }
}
