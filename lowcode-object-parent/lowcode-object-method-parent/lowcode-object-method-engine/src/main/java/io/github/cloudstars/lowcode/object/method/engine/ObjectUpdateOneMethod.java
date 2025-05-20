package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.commons.ObjectAttrNames;
import io.github.cloudstars.lowcode.object.method.editor.ObjectUpdateOneMethodConfig;

import java.util.Map;

/**
 * 单条数据更新模型方法
 *
 * @author clouds
 */
@ObjectMethodClass(name = "UPDATE_ONE", configClass = ObjectUpdateOneMethodConfig.class)
public class ObjectUpdateOneMethod extends AbstractObjectMethod<ObjectUpdateOneMethodConfig, Map<String, ObjectAttrNames>, Integer> {

    public ObjectUpdateOneMethod(ObjectUpdateOneMethodConfig methodConfig) {
        super(methodConfig);
    }

    @Override
    public Integer execute(Map<String, ObjectAttrNames> methodParams) {
        return null;
    }

}
