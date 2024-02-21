package net.cf.api.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.cf.api.commons.definition.ApiDefinition;
import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.engine.ApiDefinitionLoader;
import net.cf.api.engine.ApiExecuteDispatcher;
import net.cf.api.engine.ApiExecutorRegistration;
import net.cf.api.engine.AppLoggerFactory;
import net.cf.api.engine.data.mapping.DataMappingService;
import net.cf.api.engine.exception.ApiDispatchException;
import net.cf.api.engine.util.valid.ValidJsonSchemaHelper;
import net.cf.api.engine.util.valid.ValidResult;
import net.cf.api.provider.executor.ApiExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * api执行分发器实现
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:36
 */
public class ApiExecuteDispatcherImpl implements ApiExecuteDispatcher {

    private static final Logger log = LoggerFactory.getLogger(ApiExecuteDispatcherImpl.class);
    private final ApiExecutorRegistration apiExecutorRegistration;
    private final AppLoggerFactory appLoggerFactory;
    private final DataMappingService dataMappingService;
    private final ApiDefinitionLoader apiDefinitionLoader;

    public ApiExecuteDispatcherImpl(ApiExecutorRegistration apiExecutorRegistration, AppLoggerFactory appLoggerFactory, DataMappingService dataMappingService, ApiDefinitionLoader apiDefinitionLoader) {
        this.apiExecutorRegistration = apiExecutorRegistration;
        this.appLoggerFactory = appLoggerFactory;
        this.dataMappingService = dataMappingService;
        this.apiDefinitionLoader = apiDefinitionLoader;
    }


    /**
     * 分发api
     * @param input  输入
     * @param apiKey apiKey
     * @return 返回结果
     */
    @Override
    public Object dispatch(Object input, String apiKey) {
        ApiDefinition apiDefinition = findApiDefinition(apiKey);
        // 请求参数类型绑定
        Object reqParams = dataMappingService.convert(apiDefinition.getInput(), input);
        // 参数校验
        checkReqParams(apiDefinition.getInput(), reqParams);
        // 查找实现引擎
        ApiExecutor executor = findExecutor(apiDefinition.getType());
        // 委托实现引擎执行
        Object output = executor.run(reqParams, apiDefinition.getBizKey());
        // 响应参数类型绑定
        Object respData = dataMappingService.convert(apiDefinition.getOutput(), output);
        return respData;
    }

    private ApiDefinition findApiDefinition(String apiKey) {
        ApiDefinition apiDefinition = apiDefinitionLoader.find(apiKey);
        if (Objects.isNull(apiDefinition)) {
            throw new ApiDispatchException("api不存在");
        }
        return apiDefinition;
    }

    private Object mockReturn(ApiDefinition apiDefinition, Object input) {
        //  基于jsonSchema绑定参数
        return input;
    }
    private void checkReqParams(JSONObject input, Object reqParams) {
        if (Objects.isNull(input) || Objects.isNull(reqParams)) {
            return;
        }
        ValidResult validate = ValidJsonSchemaHelper.validate(JSON.toJSONString(input), JSON.toJSONString(reqParams));
        if (validate.isFail()) {
            log.warn("参数校验失败，校验信息如下：[{}]", validate.getErrorMsgList());
            throw new ApiDispatchException("参数校验失败");
        }
    }

    private ApiExecutor findExecutor(ApiTypeEnum type) {
        // todo判空
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException("api类型非法 type:" + type);
        }
        ApiExecutor apiExecutor = apiExecutorRegistration.find(type);
        if (Objects.isNull(apiExecutor)) {
            throw new ApiDispatchException("api执行器不存在");
        }
        return apiExecutor;
    }
}
