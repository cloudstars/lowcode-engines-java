package net.cf.api.flow.engine;

import net.cf.api.flow.engine.entity.ExecuteResult;

import java.util.HashMap;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/5
 */
public class ApiFlowExecutorImpl implements ApiFlowExecutor {
    @Override
    public ExecuteResult execute() {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setOutput(new HashMap<>());
        return executeResult;
    }
}
