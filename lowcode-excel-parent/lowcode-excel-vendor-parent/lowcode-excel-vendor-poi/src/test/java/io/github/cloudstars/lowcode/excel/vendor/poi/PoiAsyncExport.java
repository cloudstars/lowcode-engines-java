package io.github.cloudstars.lowcode.excel.vendor.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class PoiAsyncExport {

    private final static Logger logger = LoggerFactory.getLogger(PoiAsyncExport.class);

    @Test
    public void exportStatisticsData() {
        try {
            WriteExcelDataDelegated writeExcelDataDelegated = new WriteExcelDataDelegated() {
                @Override
                public void writeExcelData(Workbook workbook, SXSSFSheet eachSheet, Integer startRowCount, Integer endRowCount, Integer currentPage, Integer pageSize) throws Exception {
                    try {
                        List<Map<String, String>> dataList = null;
                        if (startRowCount < 1234) {
                            dataList = new ArrayList<>();
                            for (int i = 0; i < pageSize; i++) {
                                Map<String, String> dataMap = new HashMap<>();
                                dataMap.put("c0", "c0;" + i);
                                dataMap.put("c1", "c1;" + i);
                                dataMap.put("c2", "c2;" + i);
                                dataMap.put("c3", "c2;" + i);
                                dataMap.put("c4", "c4;" + i);
                            }
                        }


                        if (dataList != null && dataList.size() > 0) {
                            CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
                            cellStyle.setBorderTop(BorderStyle.THIN); //上边框
                            cellStyle.setBorderRight(BorderStyle.THIN); //右边框
                            cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
                            cellStyle.setBorderLeft(BorderStyle.THIN); //左边框

                            for (int i = startRowCount; i <= endRowCount; i++) {
                                SXSSFRow eachDataRow = eachSheet.createRow(i);
                                int dataArrayStartIndex = i - startRowCount;
                                if (dataArrayStartIndex < dataList.size()) {
                                    Map<String, String> dataMap = dataList.get(dataArrayStartIndex);

                                    // 商品信息
                                    eachDataRow.createCell(0).setCellValue(dataMap.get("c0"));
                                    eachDataRow.createCell(1).setCellValue(dataMap.get("c1"));
                                    eachDataRow.createCell(2).setCellValue(dataMap.get("c2"));
                                    eachDataRow.createCell(3).setCellValue(dataMap.get("c3"));
                                    eachDataRow.createCell(4).setCellValue(dataMap.get("c4"));

                                    // 记录当前写入数据
                                    writeRowNum++;
                                }
                            }
                        } else {
                            hasMore = false;
                        }
                    } catch (Exception e) {
                        logger.error("商品信息导出EXCEL异常!", e);
                    }
                }
            };
            // 文件本地保存路径
            String filePath = "src/test/resources/generator/simple/2.xlsx";
            // excel每列头名称
            String[] lineHeadArray = {"列1", "列2", "列3", "列4", "列5"};
            // 执行导出
            PoiUtil.exportExcelBigData(lineHeadArray, filePath, writeExcelDataDelegated);
        } catch (Exception e) {
            logger.error("exportStatisticsData异常", e);
        }
    }
}
