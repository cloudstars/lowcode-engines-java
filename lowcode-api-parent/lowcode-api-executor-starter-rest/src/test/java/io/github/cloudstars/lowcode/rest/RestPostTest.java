package io.github.cloudstars.lowcode.rest;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.controller.PostRemoteTestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试以Rest的方式Post请求远程服务
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestPostTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        PostRemoteTestController.PostBody postBody = new PostRemoteTestController.PostBody();
        postBody.setA("abc");
        Map<String, Object> xMap = new HashMap<>();
        xMap.put("x", "xyz");
        xMap.put("y", 123);
        postBody.setX(xMap);
        String result = this.testRestTemplate.postForObject("/remote/api/post/0", postBody, String.class);

        JsonObject expectedResultJson = JsonUtils.loadJsonObjectFromClasspath("/api/rest-post-0-result.json");
        JsonTestUtils.assertEquals(expectedResultJson, JsonUtils.toJsonObject(result));
    }

}
