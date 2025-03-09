package net.cf.excel.engine2;

import java.util.List;

/**
 * Excel生成器数据读取器函数接口
 *
 * @param <T> Excel输入的数据类型
 * @author clouds
 */
@FunctionalInterface
public interface ExcelGeneratorDataReader<T extends Object> {

    /**
     * 读取批次数据
     *
     * @param index 批次号
     * @return 当前批次读取到的Excel数据
     */
    List<T> readBatchList(int index);

}
