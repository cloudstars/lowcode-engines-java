package io.github.cloudstars.lowcode.api.executor.rest;

import io.github.cloudstars.lowcode.api.executor.invoke.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        return this.x(responseEntity);
    }

    @Override
    public ApiResponse uploadFile(ApiFileUploadRequest request) {
        String url = request.getUrl();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>(request.getFormItems());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // 设置内容类型为 multipart/form-data
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, requestEntity, String.class);
        return this.x(responseEntity);
    }

    private ApiResponse x(ResponseEntity<String> responseEntity) {
        HttpStatus httpStatus = responseEntity.getStatusCode();
        String responseEntityBody = responseEntity.getBody();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(httpStatus.value());
        apiResponse.setBody(responseEntityBody);
        return apiResponse;
    }


}
