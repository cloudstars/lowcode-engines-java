package net.cf.api.proxy.engine.filter;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;

import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 15:50
 */
public class PostExecuteFilter implements ExecuteFilter {
    @Override
    public void preHandle(HttpApiRequest httpApiRequest) {

    }

    @Override
    public void postHandle(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse) {
        Map<String, Object> headers = httpApiResponse.getHeaders();
        headers.put("postHandle-postHandle", "postHandle");
    }
}
