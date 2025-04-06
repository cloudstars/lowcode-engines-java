package net.cf.excel.engine;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-22 18:54
 * @Description: excel解析用field集合
 */
public interface ExcelTitleGroup extends ExcelTitle {
    List<SingleExcelTitle> getSubTitles();

    /**
     * 区分表头组下的数据最终解析出的格式是否是一个层级关系的集合
     *
     * @return
     */
    boolean isCollection();

    DataFormatter<List> getDataFormatter();
}
