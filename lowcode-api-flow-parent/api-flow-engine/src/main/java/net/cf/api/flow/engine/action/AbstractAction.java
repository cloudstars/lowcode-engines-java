package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.entity.ExecuteContext;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public abstract class AbstractAction {

    protected AbstractAction(JSONObject config) {
        this.config = config;
        this.type = this.getClass().getAnnotation(ActionAnno.class).type();
    }

    private String type;

    private JSONObject config;

    protected abstract void execute(ExecuteContext executeContext);

    protected void doExecute(ExecuteContext executeContext) {
        execute(executeContext);
    }

    public String getType() {
        return type;
    }
}
