package com.hotel.common;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

@Slf4j
public class AccountExcelListener extends AnalysisEventListener<T> {
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("======>>>解析异常：", exception);
    }

    @Override
    public void invoke(T t, AnalysisContext context) {
        log.info("解析到一条数据:{}", t);
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