package io.github.cloudstars.lowcode.api;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import io.github.cloudstars.lowcode.api.executor.ApiExecutor;
import io.github.cloudstars.lowcode.api.executor.ApiExecutorImpl;
import io.github.cloudstars.lowcode.api.executor.ApiResult;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestApiInvokerTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Resource
    private ApiInvoker restApiInvoke;

    @Test
    public void test() {
        String result = this.testRestTemplate.getForObject("/remote/api/get", String.class);
        Assert.assertEquals("SomeThing", result);
    }

    @Test
    public void test1() {
        JsonObject apiConfigJson = JsonUtils.loadJsonObjectFromClasspath("get-0.json");
        ApiExecutor apiExecutor = new ApiExecutorImpl(restApiInvoke);
        ApiConfig apiConfig = new ApiConfig(apiConfigJson);
        ApiResult apiResult = apiExecutor.execute(apiConfig);
        Assert.assertEquals("SomeThing", apiResult.getResult());
    }

}
