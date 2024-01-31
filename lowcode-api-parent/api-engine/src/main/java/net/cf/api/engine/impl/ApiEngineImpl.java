package net.cf.api.engine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.cf.api.commons.define.ApiDefine;
import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.engine.ApiEngine;
import net.cf.api.engine.ApiExecutorRegistration;
import net.cf.api.engine.AppLoggerFactory;
import net.cf.api.engine.data.mapping.DataMappingService;
import net.cf.api.engine.util.valid.ValidJsonSchemaHelper;
import net.cf.api.engine.util.valid.ValidResult;
import net.cf.api.provider.executor.ApiExecutor;
import net.cf.commons.lang.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:36
 */
public class ApiEngineImpl implements ApiEngine {

    private static final Logger log = LoggerFactory.getLogger(ApiEngineImpl.class);
    public ApiEngineImpl(ApiExecutorRegistration apiExecutorRegistration, AppLoggerFactory appLoggerFactory, DataMappingService dataMappingService) {
        this.apiExecutorRegistration = apiExecutorRegistration;
        this.appLoggerFactory = appLoggerFactory;
        this.dataMappingService = dataMappingService;
    }
    public final ApiExecutorRegistration apiExecutorRegistration;
    public final AppLoggerFactory appLoggerFactory;
    public final DataMappingService dataMappingService;

    @Override
    public Object run(Object input, ApiDefine apiDefine) {
//        Logger logger = appLoggerFactory.getLogger();
//        logger.info("xxx");
        // 请求参数绑定
        Object reqParams = bindParams(apiDefine.getInput(), input);
        // 参数校验
        checkReqParams(apiDefine.getInput(), reqParams);
        // mock返回
        if (Boolean.TRUE.equals(apiDefine.getMockEnabled())) {
            return mockReturn(apiDefine, input);
        }
        // 查找实现引擎
        ApiExecutor rtEngine = findRtEngine(apiDefine.getType());
        // 委托实现引擎执行
        Object output = rtEngine.run(reqParams, apiDefine.getBizKey());
        // 参数绑定
        Object respData = bindParams(apiDefine.getOutput(), output);
        return respData;
    }

    private Object mockReturn(ApiDefine apiDefine, Object input) {
        //  基于jsonSchema绑定参数
        return input;
    }

    private Object bindParams(JSONObject jsonSchema, Object input) {
        //  基于jsonSchema绑定参数
        return dataMappingService.convert(jsonSchema, input);
    }

    private void checkReqParams(JSONObject input, Object reqParams) {
        if (Objects.isNull(input) || Objects.isNull(reqParams)) {
            return;
        }
        ValidResult validate = ValidJsonSchemaHelper.validate(JSON.toJSONString(input), JSON.toJSONString(reqParams));
        if (validate.isFail()) {
            log.warn("参数校验失败，校验信息如下：[{}]", validate.getErrorMsgList());
            throw new BusinessException("参数校验失败");
        }
    }

    private ApiExecutor findRtEngine(ApiTypeEnum type) {
        // todo判空
        return apiExecutorRegistration.find(type);
    }
}
