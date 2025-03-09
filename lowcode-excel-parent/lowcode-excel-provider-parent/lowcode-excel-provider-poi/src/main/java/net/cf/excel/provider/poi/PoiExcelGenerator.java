package net.cf.excel.provider.poi;

import net.cf.excel.provider.ExcelGenerator;
import net.cf.excel.provider.config.ExcelColumnConfig;
import net.cf.excel.provider.config.ExcelGeneratorConfig;
import net.cf.excel.provider.exception.ExcelWriteException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PoiExcelGenerator<T extends Object> implements ExcelGenerator<T> {

    /**
     * Excel生成配置
     */
    private ExcelGeneratorConfig config;

    /**
     * 输出文件流
     */
    private OutputStream os;

    private Workbook wb;

    /**
     * 当行处理的行号
     */
    private int rowIndex = 0;

    public PoiExcelGenerator(ExcelGeneratorConfig config, OutputStream os) {
        this.config = config;
        this.os = os;

        //生成导出xlsx文件的类；用于创建工作簿；它提供了创建，读取和写入excel的方法
       this.wb = new HSSFWorkbook();
       this.wb.createSheet();
    }

    @Override
    public void append(int index, List<T> list) {
        List<ExcelColumnConfig> columns = config.getColumns();

        // 生成数据
        int startRow = index * 200;
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            Row row = this.wb.getSheetAt(0).createRow(startRow++);
            int ci = 0;
            for (ExcelColumnConfig column : columns) {
                Cell cell = row.createCell(ci);
                String cname = column.getName();
                cell.setCellValue(ObjectUtils.getFieldValue(item, cname).toString());
                ci++;
            }
        }

        try {//将缓冲区的内容刷进去，如果少了这一步，excel是没有内容的
            os.flush();
            //将输出流的内容写入进excel
            wb.write(os);
        } catch (IOException e) {
            throw new ExcelWriteException("EXCEL写入失败", e);
        }
    }
}
