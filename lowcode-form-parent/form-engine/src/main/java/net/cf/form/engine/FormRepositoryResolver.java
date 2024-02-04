package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.repository.FormRepository;

/**
 * 表单引擎存储层接口解析器
 *
 * @author clouds
 */
public interface FormRepositoryResolver {

    /**
     * 根据对象获取对象存储层的驱动
     *
     * @param object
     */
    FormRepository resolveRepository(XObject object);
}
