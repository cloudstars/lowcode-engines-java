package io.github.cloudstars.object.core.engine;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.object.core.editor.XObjectConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型实现
 *
 * @author clouds
 */
public class XObjectImpl implements XObject {

    /**
     * 模型配置解析器
     */
    //private XObjectConfigResolver objectConfigResolver;

    /**
     * 模型配置
     */
    private XObjectConfig objectConfig;

    /**
     * 模型数据
     */
    private Map<String, Object> dataMap = new HashMap<>();

    public XObjectImpl(/*XObjectConfigResolver objectConfigResolver, */XObjectConfig objectConfig) {
        //this.objectConfigResolver = objectConfigResolver;
        this.objectConfig = objectConfig;
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
    public Object get(String key) {
        return this.dataMap.get(key);
    }

    @Override
    public void set(String key, Object value) {
        // 校验key是否存在
        if (this.objectConfig.getField(key) == null) {
            throw new SystemException("模型[" + objectConfig.getKey() + "]中不存在字段[" + key + "]");
        }

        this.dataMap.put(key, value);
    }

}
