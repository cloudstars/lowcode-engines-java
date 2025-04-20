package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiInputConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiOutputConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ObjectValueTypeConfig;

/**
 * 单条数据插入API配置模板
 *
 * @author clouds
 */
public class InsertOneApiConfigTemplate extends AbstractApiConfigTemplate<InsertDataApiConfigParams> {

    public InsertOneApiConfigTemplate() {
        super("Insert.One", "单条数据插入API配置模板");
    }

    @Override
    public ApiConfig newInstance(InsertDataApiConfigParams params) {
        // 入参由输入的属性列表决定
        ApiInputConfig inputConfig = new ApiInputConfig();
        inputConfig.setRequired(true);
        inputConfig.setDescription("这是插入单条记录的API模板");
        ObjectValueTypeConfig inputValueType = new ObjectValueTypeConfig();
        inputValueType.setProperties(params.getProperties());
        inputConfig.setValueType(inputValueType);

        // 出参是影响行数（1 或 0）
        ApiOutputConfig outputConfig = new ApiOutputConfig();
        NumberValueTypeConfig outputValueType = new NumberValueTypeConfig();
        outputConfig.setValueType(outputValueType);

        ApiConfig apiConfig = new ApiConfig(inputConfig, outputConfig);
        return apiConfig;
    }

}
