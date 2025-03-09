package net.cf.excel.engine;

import com.alibaba.fastjson.JSONObject;
import net.cf.excel.engine.bean.ExcelBuildConfig;
import net.cf.excel.engine.bean.ExcelParseConfig;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 19:02
 * @Description: ExcelOpConfigLoader接口
 */
public interface ExcelOpConfigLoader {
    /**
     * 根据配置文件解析出ExcelParseConfig
     *
     * @param jsonObject
     * @return
     */
    ExcelParseConfig loadExcelParseConfig(JSONObject jsonObject);

    /**
     * 根据配置文件解析出ExcelBuildConfig
     *
     * @param jsonObject
     * @return
     */
    ExcelBuildConfig loadExcelBuildConfig(JSONObject jsonObject);
}
