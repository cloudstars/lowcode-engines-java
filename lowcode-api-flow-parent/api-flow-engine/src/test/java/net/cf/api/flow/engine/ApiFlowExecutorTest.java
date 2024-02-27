package net.cf.api.flow.engine;

import com.alibaba.fastjson.JSON;
import net.cf.api.flow.engine.action.StartAction;
import net.cf.api.flow.engine.constant.ContextConstant;
import net.cf.api.flow.engine.entity.ExecuteContext;
import net.cf.commons.test.util.FileUtils;
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
    
    @Test
    public void testExecute() {
        String configStr = FileUtils.loadTextFromClasspath("schema/SimpleApiFlowConfig.json");
        ApiFlowExecutor apiFlowExecutor = new ApiFlowExecutorImpl(new StartAction(JSON.parseObject(configStr)));

        // 输入
        ExecuteContext executeContext = new ExecuteContext();

        Map<String, Object> inputData = new HashMap<>();
        inputData.put("a", "b");
        executeContext.set(ContextConstant.INPUT, inputData);
        executeContext.set(ContextConstant.OUTPUT, null);

        // 执行
        apiFlowExecutor.execute(executeContext);
        // 输出
        Object responseData = executeContext.get(ContextConstant.OUTPUT);
        String result = (String) responseData;
        // 比对
        Assert.assertEquals("b", result);
    }
}
