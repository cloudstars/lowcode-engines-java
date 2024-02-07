package net.cf.api.flow.engine;

import com.alibaba.fastjson.JSON;
import net.cf.api.flow.engine.action.StartAction;
import net.cf.api.flow.engine.entity.ExecuteContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public class ApiFlowExecutorTest {

    private String config = "{\n" +
            " \"id\": \"MrpCuH4rcTvo\",\n" +
            " \"type\": \"start\",\n" +
            " \"title\": \"开始\",\n" +
            " \"ignoreError\": false,\n" +
            " \"children\": [\n" +
            "  {\n" +
            "   \"id\": \"w1OalYWKhJcS\",\n" +
            "   \"type\": \"return\",\n" +
            "   \"title\": \"返回\",\n" +
            "   \"dataType\": \"\",\n" +
            "   \"targetName\": \"{{input.a}}\",\n" +
            "   \"children\": []\n" +
            "  }\n" +
            " ]\n" +
            "}";

    @Test
    public void testExecute() {
        ExecuteContext executeContext = new ExecuteContext();
        executeContext.setConfig(JSON.parseObject(config));
        ApiFlowExecutor apiFlowExecutor = new ApiFlowExecutorImpl(new StartAction(JSON.parseObject(config)));

        // 输入
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("a", "b");
        executeContext.setRequestData(inputData);
        // 执行
        apiFlowExecutor.execute(executeContext);
        // 输出
        Map<String, Object> responseData = executeContext.getResponseData();
        String result = (String) responseData.get("a");
        // 比对
        Assert.assertEquals("b", result);
    }
}
