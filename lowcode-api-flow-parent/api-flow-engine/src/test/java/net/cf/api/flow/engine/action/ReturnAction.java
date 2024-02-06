package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONObject;
import net.cf.api.flow.engine.entity.ExecuteContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
@ActionAnno(type = "return")
public class ReturnAction extends AbstractAction {

    private String targetName;

    private String dataType;

    public ReturnAction(JSONObject config) {
        super(config);
        this.targetName = config.getString("targetName").trim();
        this.dataType = config.getString("dataType").trim();
    }

    @Override
    public void execute(ExecuteContext executeContext) {
        String inputValue = (String) executeContext.getRequestData().get("a");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("a", inputValue);
        executeContext.setResponseData(responseData);
    }
}
