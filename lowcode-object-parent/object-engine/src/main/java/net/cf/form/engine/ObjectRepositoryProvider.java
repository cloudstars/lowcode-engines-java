package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.repository.ObjectRepository;

/**
 * 模型引擎存储层接口解析器
 *
 * @author clouds
 */
public interface ObjectRepositoryProvider<O extends XObject> {

    /**
     * 根据模型获取模型存储层的驱动
     *
     * @param object
     */
    ObjectRepository getByObject(O object);
}
