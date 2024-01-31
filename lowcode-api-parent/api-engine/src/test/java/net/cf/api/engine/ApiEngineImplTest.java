package net.cf.api.engine;

import net.cf.api.commons.define.ApiDefine;
import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.engine.data.mapping.DataMappingHandler;
import net.cf.api.engine.data.mapping.DataMappingService;
import net.cf.api.engine.impl.ApiEngineImpl;
import net.cf.api.engine.impl.ApiExecutorRegistrationImpl;
import net.cf.api.provider.executor.ApiExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:37
 */
public class ApiEngineImplTest {
    private ApiExecutorRegistration apiExecutorRegistration;
    private AppLoggerFactory appLoggerFactory;
    private DataMappingService dataMappingService;
    @Before
    public void setUp() {
        List<DataMappingHandler> dataMappingHandlers = new ArrayList<>();
        dataMappingService = new DataMappingService(dataMappingHandlers);
        apiExecutorRegistration = new ApiExecutorRegistrationImpl();
        ApiExecutor apiProxyRtEngine = new ApiExecutor() {
            @Override
            public ApiTypeEnum getType() {
                return ApiTypeEnum.API_PROXY;
            }
            @Override
            public Object run(Object reqData, String bizKey) {
                return new HashMap<>();
            }
        };
        apiExecutorRegistration.register(apiProxyRtEngine);

    }

    @Test
    public void run() {
        ApiEngine apiEngine = new ApiEngineImpl(apiExecutorRegistration, appLoggerFactory, dataMappingService);
        ApiDefine apiDefine = new ApiDefine();
        apiDefine.setType(ApiTypeEnum.API_PROXY);
        Object result = apiEngine.run(new HashMap<>(), apiDefine);
        Assert.assertNotNull(result);
    }
}