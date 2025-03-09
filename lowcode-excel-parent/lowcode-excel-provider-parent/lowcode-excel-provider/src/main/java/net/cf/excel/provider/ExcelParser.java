package net.cf.excel.provider;

import java.util.List;

/**
 * Excel解析器
 *
 * @param <T> Excel数据的类型
 * @author clouds
 */
public interface ExcelParser<T> {

    /**
     * 从Excel抽取数据
     *
     * @param index 批次序号
     * @param batchSize 批次数量
     * @return 当前批次解析出来的数据
     */
    List<T> subtract(int index, int batchSize);
    
}
