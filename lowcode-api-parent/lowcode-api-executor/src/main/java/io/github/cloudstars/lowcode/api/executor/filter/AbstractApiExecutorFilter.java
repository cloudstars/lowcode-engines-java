package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.ApiResponse;

public class AbstractApiExecutorFilter implements ApiExecuteFilter {

    private ApiExecuteFilterChain filterChain;

    public AbstractApiExecutorFilter(ApiExecuteFilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public void process(ApiRequest apiRequest, ApiResponse apiResponse) {
        this.doBeforeRequest(apiRequest);
        this.filterChain.doFilter();
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
