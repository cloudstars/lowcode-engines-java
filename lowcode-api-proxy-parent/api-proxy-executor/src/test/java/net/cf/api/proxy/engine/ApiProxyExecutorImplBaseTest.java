package net.cf.api.proxy.engine;

import com.alibaba.fastjson.JSON;
import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.filter.PostExecuteFilter;
import net.cf.api.proxy.engine.filter.PreExecuteFilter;
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
public class ApiProxyExecutorImplBaseTest {
    private static final Logger log = LoggerFactory.getLogger(ApiProxyExecutorImplBaseTest.class);
    private HttpPoster httpPoster;

    @Before
    public void setUp() {
        httpPoster = requestEntity -> {
            Map<String, String> result = new HashMap<>(8);
            result.put("returnCode", "SUC0000");
            result.put("errorMsg", "成功");
            return ResponseEntity.ok(result);
        };

    }

    @Test
    public void executeEmptyFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        ApiProxyExecutor apiProxyExecutor = new ApiProxyExecutorImpl(executeFilterChain, httpPoster);
        HttpApiRequest httpApiRequest = HttpApiRequest.builder()
                .method(HttpMethod.POST)
                .url("www.baidu.com")
                .headers(new HashMap<>(8))
                .body("ccc")
                .build();
        HttpApiResponse httpApiResponse = apiProxyExecutor.execute(httpApiRequest);
        log.info("{}", JSON.toJSONString(httpApiResponse));
        Assert.assertNotNull(httpApiResponse);
    }

    @Test
    public void executePreFilterTest() {
        ExecuteFilterChain executeFilterChain = new ExecuteFilterChainImpl();
        executeFilterChain.addFilter(new PreExecuteFilter());
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
        Map<String, Object> headers = httpApiRequest.getHeaders();
        Assert.assertTrue(headers.size() > 0);
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