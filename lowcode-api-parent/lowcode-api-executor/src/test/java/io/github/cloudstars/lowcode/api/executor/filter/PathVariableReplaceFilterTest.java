package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.ApiExecutorTestApplication;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 路径变量替换过滤器测试
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiExecutorTestApplication.class)
public class PathVariableReplaceFilterTest {

    @Resource
    private PathVariableReplaceFilter filter;

    @Test
    public void test1() {
        ApiRequest apiRequest = new ApiRequest();
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", 123);
        apiRequest.setQueryParams(queryParams);
        apiRequest.setUrl("http://localhost:8080/resource/${id}/list");
        filter.process(apiRequest, null);
        Assert.assertEquals("http://localhost:8080/resource/123/list", apiRequest.getUrl());
    }

}
