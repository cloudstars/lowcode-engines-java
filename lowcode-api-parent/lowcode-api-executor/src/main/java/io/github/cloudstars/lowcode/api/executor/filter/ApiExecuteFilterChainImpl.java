package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;

import java.util.ArrayList;
import java.util.List;

public class ApiExecuteFilterChainImpl implements ApiExecuteFilterChain {

    /**
     * 过滤器列表
     */
    private List<ApiExecuteFilter> filters = new ArrayList<>();

    private int currentIndex = -1;

    public ApiExecuteFilterChainImpl() {
    }

    @Override
    public void doFilter(ApiRequest apiRequest, ApiResponse apiResponse) {
        currentIndex++;
        if (currentIndex < filters.size()) {
            filters.get(currentIndex).process(apiRequest, apiResponse);
        }
    }

    @Override
    public void addFilter(ApiExecuteFilter filter) {
        this.filters.add(filter);
    }

}
