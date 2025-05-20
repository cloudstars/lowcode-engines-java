package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.commons.ObjectAttrNames;
import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;

import java.util.Map;

/**
 * 单条数据插入模型方法
 *
 * @author clouds
 */
@ObjectMethodClass(name = "INSERT_ONE", configClass = ObjectInsertOneMethodConfig.class)
public class ObjectInsertOneMethod extends AbstractObjectMethod<ObjectInsertOneMethodConfig, Map<String, ObjectAttrNames>, Integer> {

    public ObjectInsertOneMethod(ObjectInsertOneMethodConfig methodConfig) {
        super(methodConfig);
    }

    @Override
    public Integer execute(Map<String, ObjectAttrNames> methodParams) {
        return null;
    }

}
