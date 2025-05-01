package io.github.cloudstars.lowcode.invoke;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试以API调用器的方式访问远程服务
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestApiInvokerTest {

    @Resource
    private ApiInvoker restApiInvoke;

    @Test
    public void test1() {
        // 加载API请求配置并发送请求
        String apiRequestString = FileUtils.loadTextFromClasspath("invoke/invoke-get0-request.json");
        ApiRequest apiRequest = JsonUtils.parseObject(apiRequestString, ApiRequest.class);
        ApiResponse apiResponse = this.restApiInvoke.get(apiRequest);

        // 断言与预期的结果相等
        String apiResponseString = FileUtils.loadTextFromClasspath("invoke/invoke-get0-response200.json");
        ApiResponse apiResponseExpected = JsonUtils.parseObject(apiResponseString, ApiResponse.class);
        JsonTestUtils.assertEquals(apiResponseExpected, apiResponse);
    }

}
