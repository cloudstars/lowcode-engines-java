package net.cf.api.proxy.engine.filter;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;

import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 15:50
 */
public class PreExecuteFilter implements ExecuteFilter {
    @Override
    public void preHandle(HttpApiRequest httpApiRequest) {
        Map<String, Object> headers = httpApiRequest.getHeaders();
        headers.put("prepre-pre", "pre");
    }

    @Override
    public void postHandle(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse) {

    }
}
