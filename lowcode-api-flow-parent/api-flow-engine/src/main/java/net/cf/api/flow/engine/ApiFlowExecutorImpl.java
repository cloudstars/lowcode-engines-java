package net.cf.api.flow.engine;

import net.cf.api.flow.engine.action.AbstractInitAction;
import net.cf.api.flow.engine.constant.ContextConstant;
import net.cf.api.flow.engine.entity.ExecuteContext;
import net.cf.api.flow.engine.entity.ExecuteResult;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/5
 */
public class ApiFlowExecutorImpl implements ApiFlowExecutor {

    private final AbstractInitAction abstractInitAction;

    public ApiFlowExecutorImpl(AbstractInitAction abstractInitAction) {
        this.abstractInitAction = abstractInitAction;
    }

    @Override
    public ExecuteResult execute(ExecuteContext executeContext) {
        abstractInitAction.execute(executeContext);
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setOutput(executeContext.get(ContextConstant.OUTPUT));
        return executeResult;
    }
}
