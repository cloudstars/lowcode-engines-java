package net.cf.form.engine.repository.mysql;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;

/**
 * 测试用的数据对象解析器
 *
 * @author clouds
 */
public class TestDataObjectResolver implements DataObjectResolver {

    @Override
    public DataObject resolveObject(String objectName) {
        return TestDataObjectsHolder.getObject(objectName);
    }
}
