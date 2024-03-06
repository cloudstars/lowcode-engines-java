package net.cf.excel.engine;

import com.alibaba.fastjson.JSONObject;
import net.cf.excel.engine.bean.ExcelParseConfig;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 19:02
 * @Description: ExcelOpConfigLoader接口
 */
public interface ExcelOpConfigLoader {
    ExcelParseConfig loadExcelParseConfig(JSONObject jsonObject);
}
