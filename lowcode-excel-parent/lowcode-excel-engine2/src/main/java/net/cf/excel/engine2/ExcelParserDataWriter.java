package net.cf.excel.engine2;

import java.util.List;

/**
 * Excel解析器数据写入器函数接口
 *
 * @param <T> Excel输出的数据类型
 * @author clouds
 */
@FunctionalInterface
public interface ExcelParserDataWriter<T extends Object> {

    /**
     * 回写批次数据
     *
     * @param index 批次号
     * @param list 当前批次要需要回写的Excel数据
     */
    void writeBatchList(int index, List<T> list);

}
