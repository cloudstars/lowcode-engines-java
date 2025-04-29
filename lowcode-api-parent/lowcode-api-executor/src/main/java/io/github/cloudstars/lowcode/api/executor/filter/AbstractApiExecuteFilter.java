package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;

public class AbstractApiExecuteFilter implements ApiExecuteFilter {

    private ApiExecuteFilterChain filterChain;

    public AbstractApiExecuteFilter(ApiExecuteFilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public void process(ApiRequest apiRequest, ApiResponse apiResponse) {
        this.doBeforeRequest(apiRequest);
        this.filterChain.doFilter(apiRequest, apiResponse);
        this.doAfterResponse(apiResponse);
    }

    /**
     * 在请求之前处理
     *
     * @param apiRequest
     */
    protected void doBeforeRequest(ApiRequest apiRequest) {}

    /**
     * 在响应之后处理
     *
     * @param apiResponse
     */
    protected void doAfterResponse(ApiResponse apiResponse) {}

}
