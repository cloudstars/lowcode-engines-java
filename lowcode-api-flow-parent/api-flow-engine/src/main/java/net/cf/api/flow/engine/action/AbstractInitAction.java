package net.cf.api.flow.engine.action;


import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.entity.ExecuteContext;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public abstract class AbstractInitAction extends AbstractHasChildrenAction {

    public abstract void execute(ExecuteContext executeContext);

    public AbstractInitAction(JSONObject config) {
        super(config);
    }
}
