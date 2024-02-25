package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.repository.FormRepository;

/**
 * 表单引擎存储层接口解析器
 *
 * @author clouds
 */
public interface FormRepositoryProvider<O extends XObject> {

    /**
     * 根据模型获取模型存储层的驱动
     *
     * @param object
     */
    FormRepository getByObject(O object);
}
