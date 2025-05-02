package io.github.cloudstars.lowcode.rest;


import io.github.cloudstars.lowcode.ApiExecutorRestTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;

/**
 * 测试以Rest的方式上传文件
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ApiExecutorRestTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RestUploadTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        FileSystemResource file = new FileSystemResource(".\\pom.xml");
        body.add("file", file); // 注意这里的"file"应该与后端期望的参数名一致
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // 设置内容类型为multipart/form-data
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String result = testRestTemplate.postForObject("/remote/api/upload/0", requestEntity, String.class);
        Assert.assertEquals("File uploaded successfully: http://localhost:8080/remote/api/download/pom.xml", result);
    }
}
