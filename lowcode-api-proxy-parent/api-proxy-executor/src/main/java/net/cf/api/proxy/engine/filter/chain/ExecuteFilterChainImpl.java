package net.cf.api.proxy.engine.filter.chain;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.filter.ExecuteFilter;
import net.cf.api.proxy.engine.filter.chain.ExecuteFilterChain;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤链实现
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 11:05
 */
public class ExecuteFilterChainImpl implements ExecuteFilterChain {

    private final List<ExecuteFilter> executeFilters;

    public ExecuteFilterChainImpl(List<ExecuteFilter> executeFilters) {
        this.executeFilters = executeFilters;
    }

    public ExecuteFilterChainImpl() {
        this.executeFilters = new ArrayList<>();
    }

    @Override
    public void addFilter(ExecuteFilter executeFilter) {
        this.executeFilters.add(executeFilter);
    }

    @Override
    public void applyPreHandler(HttpApiRequest httpApiRequest) {
        if (CollectionUtils.isEmpty(executeFilters)) {
            return;
        }
        for (ExecuteFilter executeFilter : executeFilters) {
            executeFilter.preHandle(httpApiRequest);
        }
    }

    @Override
    public void applyPostHandler(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse) {
        if (CollectionUtils.isEmpty(executeFilters)) {
            return;
        }
        for (ExecuteFilter executeFilter : executeFilters) {
            executeFilter.postHandle(httpApiRequest, httpApiResponse);
        }
    }
}
