package net.cf.excel.engine;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 19:02
 * @Description: ExcelTitleLoader接口
 */
public interface ExcelTitleLoader {
    List<ExcelTitle> loadTitles(JSONArray jsonArray);
}
