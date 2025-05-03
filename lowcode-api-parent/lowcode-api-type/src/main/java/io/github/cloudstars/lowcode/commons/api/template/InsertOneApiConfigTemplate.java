package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestBodyConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.response.ApiResponseConfig;
import io.github.cloudstars.lowcode.commons.value.type.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;

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
        ApiRequestConfig requestConfig = new ApiRequestConfig();
        requestConfig.setDescription("这是插入单条记录的API模板");
        ObjectValueTypeConfig requestValueType = new ObjectValueTypeConfig();
        requestValueType.setProperties(params.getProperties());
        requestConfig.setBody(new ApiRequestBodyConfig(requestValueType));

        // 出参是影响行数（1 或 0）
        ApiResponseConfig responseConfig = new ApiResponseConfig();
        NumberValueTypeConfig responseValueType = new NumberValueTypeConfig();
        responseConfig.setValueType(responseValueType);

        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setRequest(requestConfig);
        apiConfig.setResponse(responseConfig);

        return apiConfig;
    }

}
