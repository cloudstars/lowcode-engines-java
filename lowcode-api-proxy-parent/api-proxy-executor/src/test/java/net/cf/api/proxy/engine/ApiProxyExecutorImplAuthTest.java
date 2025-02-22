package net.cf.api.proxy.engine;

import com.alibaba.fastjson.JSON;
import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.enums.TokenPosEnum;
import net.cf.api.proxy.engine.filter.PostExecuteFilter;
import net.cf.api.proxy.engine.filter.builtin.auth.token.BaseTokenAuthExecuteFilter;
import net.cf.api.proxy.engine.filter.builtin.auth.token.Token;
import net.cf.api.proxy.engine.filter.builtin.auth.token.TokenAuthConfigure;
import net.cf.api.proxy.engine.filter.builtin.auth.token.TokenManager;
import net.cf.api.proxy.engine.filter.chain.ExecuteFilterChain;
import net.cf.api.proxy.engine.filter.chain.ExecuteFilterChainImpl;
import net.cf.api.proxy.engine.send.HttpPoster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 14:14
 */
public class ApiProxyExecutorImplAuthTest {
    private static final Logger log = LoggerFactory.getLogger(ApiProxyExecutorImplAuthTest.class);
    private HttpPoster httpPoster;
    private TokenAuthConfigure tokenAuthConfigure;
    private TokenManager tokenManager;
    @Before
    public void setUp() {
        httpPoster = requestEntity -> {
            Map<String, String> result = new HashMap<>(8);
            result.put("returnCode", "SUC0000");
            result.put("errorMsg", "成功");
            return ResponseEntity.ok(result);
        };
        tokenManager = new TokenManager() {
            @Override
            public Token getToken() {
                return new Token("231231231li", 1200);
            }
            @Override
            public void removeToken() {
            }
        };
    }

    @Test
    public void executePreAuthHeadersFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        tokenAuthConfigure = new TokenAuthConfigure() {
            @Override
            public TokenPosEnum getTargetPos() {
                return TokenPosEnum.HEADER;
            }

            @Override
            public String getTargetKey() {
                return "id_token";
            }
        };
        executeFilterChain.addFilter(new BaseTokenAuthExecuteFilter(tokenManager, tokenAuthConfigure));
        ApiProxyExecutor apiProxyExecutor = new ApiProxyExecutorImpl(executeFilterChain, httpPoster);
        HttpApiRequest httpApiRequest = HttpApiRequest.builder()
                .method(HttpMethod.POST)
                .url("www.baidu.com")
                .headers(new HashMap<>(8))
                .body("ccc")
                .build();
        HttpApiResponse httpApiResponse = apiProxyExecutor.execute(httpApiRequest);
        log.info("{}", JSON.toJSONString(httpApiRequest));
        log.info("{}", JSON.toJSONString(httpApiResponse));
        Assert.assertNotNull(httpApiResponse);
        Assert.assertEquals(httpApiRequest.getHeaders().get("id_token"), "231231231li");
    }
    @Test
    public void executePreAuthQueryFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        tokenAuthConfigure = new TokenAuthConfigure() {
            @Override
            public TokenPosEnum getTargetPos() {
                return TokenPosEnum.QUERY;
            }

            @Override
            public String getTargetKey() {
                return "id_token";
            }
        };
        executeFilterChain.addFilter(new BaseTokenAuthExecuteFilter(tokenManager, tokenAuthConfigure));
        ApiProxyExecutor apiProxyExecutor = new ApiProxyExecutorImpl(executeFilterChain, httpPoster);
        HttpApiRequest httpApiRequest = HttpApiRequest.builder()
                .method(HttpMethod.POST)
                .url("www.baidu.com")
                .body("ccc")
                .build();
        HttpApiResponse httpApiResponse = apiProxyExecutor.execute(httpApiRequest);
        log.info("{}", JSON.toJSONString(httpApiRequest));
        log.info("{}", JSON.toJSONString(httpApiResponse));
        Assert.assertNotNull(httpApiResponse);
        Assert.assertEquals(httpApiRequest.getQueries().get("id_token"), "231231231li");
    }

    @Test
    public void executePreAuthBodyFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        tokenAuthConfigure = new TokenAuthConfigure() {
            @Override
            public TokenPosEnum getTargetPos() {
                return TokenPosEnum.BODY;
            }

            @Override
            public String getTargetKey() {
                return "id_token";
            }
        };
        executeFilterChain.addFilter(new BaseTokenAuthExecuteFilter(tokenManager, tokenAuthConfigure));
        ApiProxyExecutor apiProxyExecutor = new ApiProxyExecutorImpl(executeFilterChain, httpPoster);
        HttpApiRequest httpApiRequest = HttpApiRequest.builder()
                .method(HttpMethod.POST)
                .url("www.baidu.com")
                .body(new HashMap<>())
                .build();
        HttpApiResponse httpApiResponse = apiProxyExecutor.execute(httpApiRequest);
        log.info("{}", JSON.toJSONString(httpApiRequest));
        log.info("{}", JSON.toJSONString(httpApiResponse));
        Assert.assertNotNull(httpApiResponse);
        Assert.assertEquals(((Map<Object, Object>) httpApiRequest.getBody()).get("id_token"), "231231231li");
    }

    @Test
    public void executePostFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        executeFilterChain.addFilter(new PostExecuteFilter());
        ApiProxyExecutor apiProxyExecutor = new ApiProxyExecutorImpl(executeFilterChain, httpPoster);
        HttpApiRequest httpApiRequest = HttpApiRequest.builder()
                .method(HttpMethod.POST)
                .url("www.baidu.com")
                .headers(new HashMap<>(8))
                .body("ccc")
                .build();
        HttpApiResponse httpApiResponse = apiProxyExecutor.execute(httpApiRequest);
        log.info("{}", JSON.toJSONString(httpApiResponse));
        Map<String, Object> headers = httpApiResponse.getHeaders();
        Assert.assertTrue(headers.size() > 0);
    }
}