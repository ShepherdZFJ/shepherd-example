package com.shepherd.basedemo.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/17 10:11
 * 导入数据的
 */
@Slf4j
public class BaseExcelListener<T> extends AnalysisEventListener<T> {

    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final Consumer<List<T>> dataHandler;

    public BaseExcelListener(Consumer<List<T>> dataHandler) {
        this.dataHandler = dataHandler;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("======>>>解析异常：", exception);
        throw exception;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            dataHandler.accept(cachedDataList);
            cachedDataList = new ArrayList<>();
        }

    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("sheet={} 所有数据解析完成！", context.readSheetHolder().getSheetName());
        dataHandler.accept(cachedDataList);
    }

}
