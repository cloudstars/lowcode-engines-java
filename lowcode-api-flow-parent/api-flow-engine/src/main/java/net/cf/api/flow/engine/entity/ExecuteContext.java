package net.cf.api.flow.engine.entity;

import com.alibaba.fastjson.JSONPath;
import net.cf.api.flow.engine.enums.ApiFlowStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public class ExecuteContext {

    /**
     * 执行上下文变量
     */
    private Map<String, Object> context;

    /**
     * 编排执行状态
     */
    private ApiFlowStatusEnum statusEnum = ApiFlowStatusEnum.NORMAL;


    /**
     * 通过path设置变量
     *
     * @param path
     * @param value
     */
    public void set(String path, Object value) {
        synchronized (this) {
            JSONPath.set(context, path, value);
        }
    }

    public Object get(String path) {
        return JSONPath.eval(context, path);
    }

    public ApiFlowStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(ApiFlowStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public ExecuteContext() {
        context = new HashMap<>();
    }

    public ExecuteContext(Map<String, Object> dataMap) {
        context = new HashMap<>();
    }
}
