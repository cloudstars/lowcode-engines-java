package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.constant.ActionDefineConstant;
import net.cf.api.flow.engine.entity.ExecuteContext;

import java.util.List;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public abstract class AbstractHasChildrenAction extends AbstractAction {

    protected List<AbstractAction> actions;

    protected abstract void execute(ExecuteContext executeContext);

    protected AbstractHasChildrenAction(JSONObject config) {
        super(config);

        JSONArray children = config.getJSONArray(ActionDefineConstant.CHILDREN_KEY);
        this.actions = ActionBuilder.build(children);
    }

    public List<AbstractAction> getActions() {
        return actions;
    }
}
