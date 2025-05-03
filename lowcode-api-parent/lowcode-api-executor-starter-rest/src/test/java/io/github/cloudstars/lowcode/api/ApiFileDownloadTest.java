package io.github.cloudstars.lowcode.api;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import io.github.cloudstars.lowcode.api.executor.ApiExecutor;
import io.github.cloudstars.lowcode.api.executor.ApiExecutorImpl;
import io.github.cloudstars.lowcode.api.executor.ApiResult;
import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试以低代码API的方式从远程服务文件下载
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class ApiFileDownloadTest {

    @Resource
    private ApiInvoker restApiInvoke;

    @Resource
    private ApiExecuteFilterChain filterChain;

    @Test
    public void test1() {
        JsonObject apiConfigJson = JsonUtils.loadJsonObjectFromClasspath("api/api-file-download-0.json");
        ApiExecutor apiExecutor = new ApiExecutorImpl(restApiInvoke, filterChain);

        ApiConfig apiConfig = new ApiConfig(apiConfigJson);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fileName", "pom.xml");
        ApiResult apiResult = apiExecutor.execute(apiConfig, paramMap);
        Assert.assertTrue(apiResult.getBody().toString().startsWith("<?xml"));
    }

}
