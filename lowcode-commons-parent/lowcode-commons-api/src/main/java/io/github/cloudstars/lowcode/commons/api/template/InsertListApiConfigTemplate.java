package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiInputConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiOutputConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.ObjectValueTypeConfig;

/**
 * 批量数据插入API配置模板
 *
 * @author clouds
 */
public class InsertListApiConfigTemplate extends AbstractApiConfigTemplate<InsertDataApiConfigParams> {

    public InsertListApiConfigTemplate() {
        super("Insert.List", "批量数据插入API配置模板");
    }

    @Override
    public ApiConfig newInstance(InsertDataApiConfigParams params) {
        // 入参由输入的属性列表决定，数组下的元素是对象
        ApiInputConfig inputConfig = new ApiInputConfig();
        ArrayValueTypeConfig inputValueType = new ArrayValueTypeConfig();
        ObjectValueTypeConfig objectValueType = new ObjectValueTypeConfig();
        objectValueType.setProperties(params.getProperties());
        inputValueType.setItemsValueType(objectValueType);
        inputValueType.setItemsRequired(true);
        inputConfig.setValueType(inputValueType);

        // 出参是影响行数（n 或 0）
        ApiOutputConfig outputConfig = new ApiOutputConfig();
        NumberValueTypeConfig outputValueType = new NumberValueTypeConfig();
        outputConfig.setValueType(outputValueType);

        ApiConfig apiConfig = new ApiConfig(inputConfig, outputConfig);
        return apiConfig;
    }

}
