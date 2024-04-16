package com.shepherd.basedemo.excel.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.NumberUtils;
import com.alibaba.excel.util.StringUtils;

import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/15 11:53
 */
public class GenderConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 从excel读数据时候调用
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        String value = cellData.getStringValue();
        if (StringUtils.isBlank(value)){
            // 未知
            return 2;
        }
        if (value.indexOf('男') != -1) {
            return 0;
        }
        if (value.indexOf('女') != -1) {
            return 1;
        }
        return 2;
    }

    /**
     * 写数据到excel里面
     * @param context
     * @return
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
        Integer value = context.getValue();
        if (Objects.equals(value, 0)) {
           return new WriteCellData<>("男");
        }
        if (Objects.equals(value, 1)) {
            return new WriteCellData<>("女");
        }
        return new WriteCellData<>("未知");
    }
}
