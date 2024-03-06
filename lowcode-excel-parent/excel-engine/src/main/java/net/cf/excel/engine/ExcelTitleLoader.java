package net.cf.excel.engine;

import com.alibaba.fastjson.JSONObject;
import net.cf.excel.engine.bean.ExcelParseConfig;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 19:02
 * @Description: ExcelTitleLoader接口
 */
public interface ExcelTitleLoader {
    ExcelParseConfig loadExcelParseConfig(JSONObject jsonObject);
}
