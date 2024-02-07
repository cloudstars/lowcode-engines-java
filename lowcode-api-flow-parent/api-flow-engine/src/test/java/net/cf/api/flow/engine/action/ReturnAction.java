package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.constant.ContextConstant;
import net.cf.api.flow.engine.entity.ExecuteContext;
import net.cf.api.flow.engine.util.ExprUtil;

import static net.cf.api.flow.engine.constant.ActionDefineConstant.TARGET_NAME;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
@ActionAnno(type = "return")
public class ReturnAction extends AbstractAction {

    private String targetName;

    public ReturnAction(JSONObject config) {
        super(config);
        this.targetName = config.getString(TARGET_NAME).trim();
    }

    @Override
    public void execute(ExecuteContext executeContext) {
        String expression = ExprUtil.getExpressionByTargetName(targetName);
        Object outputValue = executeContext.get(expression);
        executeContext.set(ContextConstant.OUTPUT, outputValue);
    }
}
