package net.cf.excel.provider;

import java.util.List;

/**
 * Excel生成器
 *
 * @param <T> Excel数据的类型
 * @author clouds
 */
public interface ExcelGenerator<T> {

    /**
     * 向Excel追加数据
     *
     * @param index 批号序号
     * @param list 批次数据
     */
    void append(int index, List<T> list);

    /**
     * 追加数据结束
     */
    default void finish() {}
    
}
