package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ObjectValueTypeConfig;

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
        ArrayValueTypeConfig inputDataType = new ArrayValueTypeConfig();
        ObjectValueTypeConfig objectDataType = new ObjectValueTypeConfig();
        objectDataType.setProperties(params.getProperties());
        inputDataType.setItemsDataType(objectDataType);
        // 出参是影响行数（n 或 0）
        NumberValueTypeConfig outputDataType = new NumberValueTypeConfig();

        ApiConfig apiConfig = new ApiConfig(inputDataType, outputDataType);
        return apiConfig;
    }

}
