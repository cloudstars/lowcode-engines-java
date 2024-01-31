package net.cf.form.engine.fieldprocessor;

import net.cf.form.engine.object.XObject;

import java.util.Map;

/**
 * 对象字段处理接口
 * @author liuyangjunwu
 */
@Deprecated
public interface ObjectFieldProcessor {

    /**
     * 获取字段类型
     * @return
     */
    String getType();


    /**
     * 设置对象值
     * @param dataMap
     * @param object
     */
    void setValue(Map<String, Object> dataMap, XObject object);
}
