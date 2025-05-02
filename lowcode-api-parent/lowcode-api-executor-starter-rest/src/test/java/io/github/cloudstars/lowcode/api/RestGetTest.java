package io.github.cloudstars.lowcode.api;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试以Rest的方式直接访问远程服务
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestGetTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        String result = this.testRestTemplate.getForObject("/remote/api/get/0", String.class);
        Assert.assertEquals("SomeThing", result);
    }

}
