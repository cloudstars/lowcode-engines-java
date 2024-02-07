package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.entity.ExecuteContext;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
@ActionAnno(type = "start")
public class StartAction extends AbstractInitAction {
    public StartAction(JSONObject config) {
        super(config);
    }

    @Override
    public void execute(ExecuteContext executeContext) {
        for (AbstractAction action : actions) {
            action.doExecute(executeContext);
        }
    }
}
