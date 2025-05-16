package io.github.cloudstars.object.core.engine;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.object.commons.XObjectConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型实现
 *
 * @author clouds
 */
public class XObjectImpl implements XObject {

    /**
     * 模型配置
     */
    private XObjectConfig objectConfig;

    /**
     * 模型数据
     */
    private Map<String, Object> dataMap = new HashMap<>();

    public XObjectImpl(XObjectConfig objectConfig) {
        this.objectConfig = objectConfig;
    }

    @Override
    public XObjectConfig getObjectConfig() {
        return this.objectConfig;
    }

    @Override
    public String getId() {
        String primaryFieldName = objectConfig.getPrimaryField().getName();
        Object primaryValue = this.dataMap.get(primaryFieldName);
        if (primaryValue instanceof String) {
            return (String) primaryValue;
        }

        return primaryValue.toString();
    }

    @Override
    public Object getValue(String key) {
        return this.dataMap.get(key);
    }

    @Override
    public void setValue(String key, Object value) {
        // 校验key是否存在
        if (this.objectConfig.getField(key) == null) {
            throw new SystemException("模型[" + objectConfig.getKey() + "]中不存在字段[" + key + "]");
        }

        this.dataMap.put(key, value);
    }

    @Override
    public void validate(Map<String, Object> dataMap) {

    }
    
}
