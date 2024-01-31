package net.cf.api.proxy.engine.filter.builtin.auth.token;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.enums.TokenPosEnum;
import net.cf.api.proxy.engine.filter.builtin.auth.AuthExecuteFilter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:15
 */
public class BaseTokenAuthExecuteFilter implements AuthExecuteFilter {

    private final TokenFactory tokenFactory;
    private final TokenAuthConfig tokenAuthConfig;

    public BaseTokenAuthExecuteFilter(TokenFactory tokenFactory, TokenAuthConfig tokenAuthConfig) {
        this.tokenFactory = tokenFactory;
        this.tokenAuthConfig = tokenAuthConfig;
    }

    @Override
    public void preHandle(HttpApiRequest httpApiRequest) {
        Token token = tokenFactory.getToken();
        TokenPosEnum tokenPos = tokenAuthConfig.getTargetPos();
        String key = tokenAuthConfig.getTargetKey();
        switch (tokenPos) {
            case QUERY:
                Map<String, Object> queries = httpApiRequest.getQueries();
                queries.put(key, token.getData());
                break;
            case HEADER:
                Map<String, Object> headers = httpApiRequest.getHeaders();
                headers.put(key, token.getData());
                break;
            case BODY:
                Object body = httpApiRequest.getBody();
                if (body instanceof Map) {
                    Map<String, String> map = (Map<String, String>) body;
                    map.put(key, token.getData());
                }
        }
    }

    @Override
    public void postHandle(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse) {
        if (HttpStatus.FORBIDDEN.value() == httpApiResponse.getCode() || HttpStatus.UNAUTHORIZED.value() == httpApiResponse.getCode()) {
            tokenFactory.removeToken();
        }
    }
}
