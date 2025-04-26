package io.github.cloudstars.lowcode.bpm.commons.config.user.assignee;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 任务分派配置
 *
 * @author clouds
 */
public class AssigneeConfig extends AbstractConfig {

    /**
     * 任务分派模式
     */
    private String type;

    /**
     * 任务分派信息
     */
    private Object value;


    /**
     * 是否多人处理
     */
    private boolean multiple;

    /**
     * 多个处理时是否串行
     */
    private boolean serialize;

    public AssigneeConfig() {
    }

    public AssigneeConfig(JsonObject configJson) {
        super(configJson);

        this.type = (String) configJson.get(XTypedConfig.ATTR);
        this.value = configJson.get(GlobalAttrNames.ATTR_VALUE);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isSerialize() {
        return serialize;
    }

    public void setSerialize(boolean serialize) {
        this.serialize = serialize;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XTypedConfig.ATTR, this.type);
        configJson.put(GlobalAttrNames.ATTR_VALUE, this.value);
        return configJson;
    }

}
