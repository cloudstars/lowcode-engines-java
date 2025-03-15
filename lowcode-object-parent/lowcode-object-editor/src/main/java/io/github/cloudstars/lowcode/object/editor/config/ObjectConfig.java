package io.github.cloudstars.lowcode.object.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型配置
 *
 * @author clouds
 */
public class ObjectConfig implements XConfig {

    /**
     * 编号
     */
    private String key;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 字段列表
     */
    private List<AbstractFieldConfig> fields;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AbstractFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractFieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public String toJsonString() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("key", this.key);
        dataMap.put("code", this.code);
        dataMap.put("name", this.name);
        dataMap.put("fields", this.fields);
        return JsonUtils.toJsonString(dataMap);
    }

}
