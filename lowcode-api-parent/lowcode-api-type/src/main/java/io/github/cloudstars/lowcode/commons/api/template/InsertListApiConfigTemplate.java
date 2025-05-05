package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestBodyConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.response.ApiResponseConfig;
import io.github.cloudstars.lowcode.commons.value.type.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;

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
        ApiRequestConfig requestConfig = new ApiRequestConfig();
        ArrayValueTypeConfig requestValueType = new ArrayValueTypeConfig();
        ObjectValueTypeConfig objectValueType = new ObjectValueTypeConfig();
        //objectValueType.setProperties(params.getProperties());
        requestValueType.setRequired(true);
        requestConfig.setBody(new ApiRequestBodyConfig(requestValueType));

        // 出参是影响行数（n 或 0）
        ApiResponseConfig responseConfig = new ApiResponseConfig();
        NumberValueTypeConfig responseValueType = new NumberValueTypeConfig();
        responseConfig.setValueType(responseValueType);

        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setRequest(requestConfig);
        apiConfig.setResponse(responseConfig);

        return apiConfig;
    }

}
