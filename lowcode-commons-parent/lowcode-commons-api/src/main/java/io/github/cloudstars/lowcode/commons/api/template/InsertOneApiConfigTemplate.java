package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.data.value.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.ObjectValueTypeConfig;

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
        ObjectValueTypeConfig inputDataType = new ObjectValueTypeConfig();
        inputDataType.setProperties(params.getProperties());
        // 出参是影响行数（1 或 0）
        NumberValueTypeConfig outputDataType = new NumberValueTypeConfig();

        ApiConfig apiConfig = new ApiConfig(inputDataType, outputDataType);
        return apiConfig;
    }

}
