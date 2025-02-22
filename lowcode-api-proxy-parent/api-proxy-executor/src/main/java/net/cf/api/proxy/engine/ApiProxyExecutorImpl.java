package net.cf.api.proxy.engine;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.filter.chain.ExecuteFilterChain;
import net.cf.api.proxy.engine.send.HttpPoster;
import net.cf.api.proxy.engine.util.MultiMapHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * api代理执行器实现
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 11:03
 */
public class ApiProxyExecutorImpl implements ApiProxyExecutor {
    private final ExecuteFilterChain executeFilterChain;
    private final HttpPoster httpPoster;

    public ApiProxyExecutorImpl(ExecuteFilterChain executeFilterChain, HttpPoster httpPoster) {
        this.executeFilterChain = executeFilterChain;
        this.httpPoster = httpPoster;
    }

    @Override
    public HttpApiResponse execute(HttpApiRequest httpApiRequest) {
        executeFilterChain.applyPreHandler(httpApiRequest);
        RequestEntity requestEntity = convertRequestEntity(httpApiRequest);
        ResponseEntity responseEntity = httpPoster.send(requestEntity);
        HttpApiResponse httpApiResponse = convertHttpApiResponse(responseEntity);
        executeFilterChain.applyPostHandler(httpApiRequest, httpApiResponse);
        return httpApiResponse;
    }


    private RequestEntity convertRequestEntity(HttpApiRequest httpApiRequest) {
        RequestEntity.BodyBuilder requestBuilder = RequestEntity
                .method(httpApiRequest.getMethod(), httpApiRequest.getUrl())
                .headers(new HttpHeaders(MultiMapHelper.toMultiMap(httpApiRequest.getHeaders())));
        return requestBuilder.body(httpApiRequest.getBody());
    }

    private HttpApiResponse convertHttpApiResponse(ResponseEntity responseEntity) {
        HttpApiResponse httpApiResponse = new HttpApiResponse();
        httpApiResponse.setBody(responseEntity.getBody());
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        Map<String, Object> headers = new HashMap<>(httpHeaders.size());
        headers.putAll(httpHeaders);
        httpApiResponse.setHeaders(headers);
        httpApiResponse.setCode(responseEntity.getStatusCodeValue());
        return httpApiResponse;
    }
}