package net.cf.form.engine.object;

import net.cf.form.engine.XObjectResolver;

/**
 * 测试用的业务对象解析器
 *
 * @author clouds
 */
@Deprecated
public class TestObjectResolver implements XObjectResolver {

    @Override
    public XObject resolveObject(String objectName) {
        return TestObjectsHolder.getObject(objectName);
    }
}
