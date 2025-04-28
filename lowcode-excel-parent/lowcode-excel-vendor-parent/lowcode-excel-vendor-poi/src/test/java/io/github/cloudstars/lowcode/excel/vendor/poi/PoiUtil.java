package io.github.cloudstars.lowcode.excel.vendor.poi;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class PoiUtil {

    private static final Logger logger = LoggerFactory.getLogger(PoiUtil.class);

    private static final int PER_WRITE_ROW_COUNT = 200;
    private static final int PER_SHEET_WRITE_COUNT = 1000;

    /**
     * <一句话功能简述> 导出大量数据到Excel
     * <功能详细描述>
     * author: zhanggw
     * @param lineHeadArray 列头信息
     * @param exportPath 导出路径
     * @param writeExcelDataDelegated 向excel写数据委托类，根据业务实现
     */
    public static void exportExcelBigData(String[] lineHeadArray, String exportPath, WriteExcelDataDelegated writeExcelDataDelegated) throws Exception {
        logger.info("开始导出数据到excel,导出路径:{}", exportPath);

        // 初始化EXCEL
        SXSSFWorkbook wb = initExcelSimple(lineHeadArray);

        // 调用委托类分批写入数据
        SXSSFSheet sheet = wb.getSheetAt(0);
        // 每次从mysql读取后写入到excel的最大数据量
        int pageSize = PER_WRITE_ROW_COUNT;
        for (int currentWriteNum = 1; currentWriteNum <= PER_SHEET_WRITE_COUNT; currentWriteNum++) { // 最多分PER_SHEET_WRITE_COUNT批写入
            if(!writeExcelDataDelegated.isHasMore()){ // 没有更多数据,停止获取写入
                break;
            }

            // excel写数据的起始位置
            int startRowCount = (currentWriteNum - 1) * pageSize + 1;
            // excel写数据的结束位置
            int endRowCount = startRowCount + pageSize - 1;
            logger.debug("startRowCount:{},endRowCount:{},getWriteRowNum:{}", startRowCount, endRowCount, writeExcelDataDelegated.getWriteRowNum());
            writeExcelDataDelegated.writeExcelData(wb, sheet, startRowCount, endRowCount, currentWriteNum, pageSize);
        }

        // 保存EXCEL到本地
        downLoadExcelToLocalPath(wb, exportPath);
        logger.info("导出完成,导出总记录数:{},导出路径:{}", writeExcelDataDelegated.getWriteRowNum(), exportPath);
    }


    /**
     * <一句话功能简述> 初始化excel
     * author: zhanggw
     * 创建时间:  2022/10/8
     * @param lineHeadArray 每列头信息，如["学号","姓名"]
     */
    private static SXSSFWorkbook initExcelSimple(String[] lineHeadArray) {
        // 在内存当中保留100行,超过的数据放到硬盘中
        SXSSFWorkbook wb = new SXSSFWorkbook(100);

        // excel样式
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        headerCellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        headerCellStyle.setBorderLeft(BorderStyle.THIN); //左边框
        headerCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        headerCellStyle.setBorderRight(BorderStyle.THIN); //右边框

        // 填充第一行每列头信息
        SXSSFSheet sheet = wb.createSheet("sheet1");
        SXSSFRow headRow = sheet.createRow(0);
        for (int k = 0; k < lineHeadArray.length; k++) {
            SXSSFCell headRowCell = headRow.createCell(k);
            headRowCell.setCellStyle(headerCellStyle);
            headRowCell.setCellValue(lineHeadArray[k]);
        }
        return wb;
    }

    /**
     * <一句话功能简述> 保存excel到本地
     * author: zhanggw
     * 创建时间:  2019/06/18 14:39
     * @param wb excel对象
     * @param exportPath 保存路径
     */
    private static void downLoadExcelToLocalPath(SXSSFWorkbook wb, String exportPath) {
        FileOutputStream fops = null;
        BufferedOutputStream bos = null;
        try {
            fops = new FileOutputStream(exportPath);
            bos = new BufferedOutputStream(fops);
            wb.write(bos);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (null != wb) {
                try {
                    wb.dispose();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (null != fops) {
                try {
                    fops.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }


}
