package net.cf.form.engine;

import net.cf.form.engine.object.XObject;

import java.util.Map;

/**
 * 对象记录处理
 */
@Deprecated
public interface ObjectRecordResolver {
    /**
     * 动态值计算
     * @param object
     * @param data
     */
    void calculate(XObject object, Map<String, Object> data);
}
