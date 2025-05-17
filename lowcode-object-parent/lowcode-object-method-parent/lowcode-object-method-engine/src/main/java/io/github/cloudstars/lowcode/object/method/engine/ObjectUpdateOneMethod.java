package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.commons.ObjectAttrNames;
import io.github.cloudstars.lowcode.object.method.editor.ObjectUpdateOneMethodConfig;
import io.github.cloudstars.object.engine.XObject;

import java.util.Map;

/**
 * 单条数据更新模型方法
 *
 * @author clouds
 */
@ObjectMethodClass(name = "UPDATE_ONE", configClass = ObjectUpdateOneMethodConfig.class)
public class ObjectUpdateOneMethod extends AbstractObjectMethod<ObjectUpdateOneMethodConfig, Integer, Map<String, ObjectAttrNames>> {

    public ObjectUpdateOneMethod(ObjectUpdateOneMethodConfig methodConfig) {
        super(methodConfig);
    }


    @Override
    public Integer execute(XObject object) {
        return null;
    }

}
