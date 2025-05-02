package io.github.cloudstars.lowcode.rest;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试以Rest的方式下载文件
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestDownloadTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        String downloadUrl = "http://localhost:8080/remote/api/download/pom.xml";
        String result = this.testRestTemplate.getForObject(downloadUrl, String.class);
        Assert.assertTrue(result.startsWith("<?xml"));
    }

}
