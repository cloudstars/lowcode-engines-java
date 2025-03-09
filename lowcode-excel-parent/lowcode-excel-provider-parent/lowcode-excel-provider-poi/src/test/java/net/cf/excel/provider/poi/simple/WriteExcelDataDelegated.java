package net.cf.excel.provider.poi.simple;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
 
/**
 * <一句话功能简述> 导出excel写数据委托类
 * <功能详细描述> 跟POIUtil的exportExcelBigData方法一起使用
 * author: zhanggw
 * 创建时间:  2022/10/8
 */
public abstract class WriteExcelDataDelegated {
    protected Long writeRowNum = 0L; // 已写入excel表的行数,在writeExcelData方法中设置
    protected boolean hasMore = true; // 是否还有更多数据待写入excel，在writeExcelData方法中设置
 
    /**
     * <一句话功能简述> excel写数据委托类，针对不同的情况自行实现
     * <功能详细描述>
     * author: zhanggw
     * 创建时间:  2019/06/18 14:33
     * @param currentSheet 当前写入excel的sheet页
     * @param startRowCount 当前写入excel的开始行
     * @param endRowCount 当前写入excel的结束行
     * @param currentPage 分批查询起始页码
     * @param pageSize 分批查询每次查询数据量
     */
    public abstract void writeExcelData(Workbook workbook, SXSSFSheet currentSheet, Integer startRowCount, Integer endRowCount, Integer currentPage, Integer pageSize) throws Exception;
 
    public Long getWriteRowNum(){
        return writeRowNum;
    }
 
    public void setWriteRowNum(Long writeRowNum) {
        this.writeRowNum = writeRowNum;
    }
 
    public boolean isHasMore() {
        return hasMore;
    }
 
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
 
}