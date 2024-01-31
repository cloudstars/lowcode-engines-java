package net.cf.form.engine;

import net.cf.form.engine.object.XObject;

/**
 * XObject解析器
 *
 * @author clouds
 */
public interface XObjectResolver {

    /**
     * 解析XObject
     *
     * @param objectName 业务对象的名称
     * @return
     */
    XObject resolveObject(String objectName);
}
