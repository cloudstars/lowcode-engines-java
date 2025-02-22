package net.cf.api.engine;

import net.cf.api.commons.definition.ApiDefinition;
import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.engine.data.mapping.DataMappingHandler;
import net.cf.api.engine.data.mapping.DataMappingService;
import net.cf.api.engine.exception.ApiDispatchException;
import net.cf.api.engine.impl.ApiExecuteDispatcherImpl;
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
public class ApiExecuteDispatcherImplTest {
    private ApiExecutorRegistration apiExecutorRegistration;
    private AppLoggerFactory appLoggerFactory;
    private DataMappingService dataMappingService;
    private ApiDefinitionLoader apiDefinitionLoader;

    @Before
    public void setUp() {
        List<DataMappingHandler> dataMappingHandlers = new ArrayList<>();
        dataMappingService = new DataMappingService(dataMappingHandlers);
        apiExecutorRegistration = new ApiExecutorRegistrationImpl();
        apiDefinitionLoader = apiKey -> {
            if ("error".equals(apiKey)) {
                ApiDefinition apiDefinition = new ApiDefinition();
                apiDefinition.setType(null);
                return apiDefinition;
            }
            if ("notFound".equals(apiKey)) {
                return null;
            }
            if ("notFoundExecutor".equals(apiKey)) {
                ApiDefinition apiDefinition = new ApiDefinition();
                apiDefinition.setType(ApiTypeEnum.API_FUNC);
                return apiDefinition;
            }
            ApiDefinition apiDefinition = new ApiDefinition();
            apiDefinition.setType(ApiTypeEnum.API_PROXY);
            apiDefinition.setKey(apiKey);
            apiDefinition.setBizKey(apiKey);
            return apiDefinition;
        };
        ApiExecutor apiProxy = new ApiExecutor() {
            @Override
            public ApiTypeEnum getType() {
                return ApiTypeEnum.API_PROXY;
            }

            @Override
            public Object run(Object reqData, String bizKey) {
                return new HashMap<>();
            }
        };
        apiExecutorRegistration.register(apiProxy);

    }

    @Test
    public void dispatcherSimpleTest() {
        ApiExecuteDispatcher dispatcher = new ApiExecuteDispatcherImpl(apiExecutorRegistration, appLoggerFactory, dataMappingService, apiDefinitionLoader);
        String apiKey = "1231231";
        Object result = dispatcher.dispatch(new HashMap<>(), apiKey);
        Assert.assertNotNull(result);
    }

    @Test
    public void dispatcherErrorApiTest() {
        ApiExecuteDispatcher dispatcher = new ApiExecuteDispatcherImpl(apiExecutorRegistration, appLoggerFactory, dataMappingService, apiDefinitionLoader);
        String apiKey = "error";
        Assert.assertThrows(IllegalArgumentException.class, () -> dispatcher.dispatch(new HashMap<>(), apiKey));
    }

    @Test
    public void dispatcherApiNotFoundTest() {
        ApiExecuteDispatcher dispatcher = new ApiExecuteDispatcherImpl(apiExecutorRegistration, appLoggerFactory, dataMappingService, apiDefinitionLoader);
        String apiKey = "notFound";
        Assert.assertThrows(ApiDispatchException.class, () -> dispatcher.dispatch(new HashMap<>(), apiKey));
    }

    @Test
    public void dispatcherExecutorNotFoundTest() {
        ApiExecuteDispatcher dispatcher = new ApiExecuteDispatcherImpl(apiExecutorRegistration, appLoggerFactory, dataMappingService, apiDefinitionLoader);
        String apiKey = "notFoundExecutor";
        Assert.assertThrows(ApiDispatchException.class, () -> dispatcher.dispatch(new HashMap<>(), apiKey));
    }
}