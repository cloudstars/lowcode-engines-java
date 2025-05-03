package io.github.cloudstars.lowcode.api;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import io.github.cloudstars.lowcode.api.executor.ApiExecutor;
import io.github.cloudstars.lowcode.api.executor.ApiExecutorImpl;
import io.github.cloudstars.lowcode.api.executor.ApiResult;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试以低代码API的方式文件上传远程服务
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class ApiFileUploadTest {

    @Resource
    private ApiInvoker restApiInvoke;

    @Test
    public void test1() {
        JsonObject apiConfigJson = JsonUtils.loadJsonObjectFromClasspath("api/api-file-upload-0.json");
        ApiExecutor apiExecutor = new ApiExecutorImpl(restApiInvoke);
        ApiConfig apiConfig = new ApiConfig(apiConfigJson);
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("a", Arrays.asList("xyz"));
        postBody.put("file", Arrays.asList(new FileSystemResource(".\\pom.xml")));

        ApiResult apiResult = apiExecutor.execute(apiConfig, postBody);
        JsonObject expectedResultJson = JsonUtils.loadJsonObjectFromClasspath("/api/api-file-upload-0-result.json");
        JsonTestUtils.assertEquals(expectedResultJson, apiResult);
    }

}
