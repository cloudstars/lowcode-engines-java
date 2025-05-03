package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.commons.lang.util.StringUtils;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * URL路径变量替换过滤器
 *
 * @author clouds
 */
public class PathVariableReplaceFilter extends AbstractApiExecuteFilter {

    /**
     * 变量规则
     */
    private static final Pattern VAR_PATTERN = Pattern.compile("\\{([\\w_]+)\\}");

    public PathVariableReplaceFilter(ApiExecuteFilterChain filterChain) {
        super(filterChain);
    }

    @Override
    protected void doBeforeRequest(ApiRequest apiRequest) {
        String url = apiRequest.getUrl();
        Map<String, Object> queryParams = apiRequest.getQueryParams();
        if (queryParams == null || queryParams.size() == 0) {
            return;
        }

        apiRequest.setUrl(StringUtils.replaceVariablePlaceholder(url, queryParams));
    }

}
