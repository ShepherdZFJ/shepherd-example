package com.shepherd.basedemo.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.util.ConverterUtils;
import com.shepherd.basedemo.entity.Account;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/15 15:06
 */
@Slf4j
public class AccountExcelListener extends AnalysisEventListener<Account> {
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
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

    /**
     * 解析表头数据
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
       log.info("表头数据：{}", ConverterUtils.convertToStringMap(headMap, context));
    }


}
