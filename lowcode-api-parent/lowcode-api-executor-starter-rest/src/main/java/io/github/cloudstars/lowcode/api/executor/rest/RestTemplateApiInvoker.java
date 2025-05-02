package io.github.cloudstars.lowcode.api.executor.rest;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiHttpHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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
        return this.getForEntity(request);
    }


    /**
     * 发送 GET 请求
     *
     * @param apiRequest API请求
     * @return
     */
    private ApiResponse getForEntity(ApiRequest apiRequest) {
        String url = apiRequest.getUrl();
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(url, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        String responseEntityBody = responseEntity.getBody();
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        MediaType contentType = httpHeaders.getContentType();
        ApiHttpHeader contentTypeHeader = new ApiHttpHeader();
        contentTypeHeader.setName("contentType");
        contentTypeHeader.setValue(contentType.toString());
        ApiResponse response = new ApiResponse();
        response.setStatus(httpStatus.value());
        response.setHeaders(Arrays.asList(contentTypeHeader));
        response.setBody(responseEntityBody);
        return response;
    }

    @Override
    public ApiResponse post(ApiRequest request) {
        String url = request.getUrl();
        Object body = request.getBody();
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, body, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        String responseEntityBody = responseEntity.getBody();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(httpStatus.value());
        apiResponse.setBody(responseEntityBody);
        return apiResponse;
    }

}
