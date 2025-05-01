package io.github.cloudstars.lowcode.api.executor.rest;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 基于RestTemplate实现的Api调用器
 *
 * @author clouds
 */
public class RestTemplateApiInvoker implements ApiInvoker {

    private RestTemplate restTemplate;

    public RestTemplateApiInvoker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ApiResponse get(ApiRequest request) {
        String url = request.getUrl();
        String result = this.getForEntity(url);
        ApiResponse response = new ApiResponse();
        response.setBody(result);
        return response;
    }


    /**
     * 发送 GET 请求
     *
     * @param url 目标地址
     * @return
     */
    private String getForEntity(String url) {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(url, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        return responseEntity.getBody();
    }

    @Override
    public ApiResponse post(ApiRequest request) {
        return null;
    }


}
