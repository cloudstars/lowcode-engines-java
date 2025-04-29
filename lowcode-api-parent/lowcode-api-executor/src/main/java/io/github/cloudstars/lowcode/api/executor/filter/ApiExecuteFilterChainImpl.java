package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;

import java.util.List;

public class ApiExecuteFilterChainImpl implements ApiExecuteFilterChain {

    private List<? extends ApiExecuteFilter> filters;

    private int currentIndex = 0;

    public ApiExecuteFilterChainImpl(List<? extends ApiExecuteFilter> filters) {
        this.filters = filters;
    }

    @Override
    public ApiExecuteFilterChain doFilter(ApiRequest apiRequest, ApiResponse apiResponse) {
        if (currentIndex < filters.size()) {
            filters.get(currentIndex).process(apiRequest, apiResponse);
            currentIndex++;
        } else {
            throw new SystemException("API执行过滤链越界，当前索引：" + currentIndex);
        }

        return this;
    }
}
