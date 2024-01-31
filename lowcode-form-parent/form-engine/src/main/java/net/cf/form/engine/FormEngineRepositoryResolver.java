package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.repository.FormEngineRepository;

/**
 * 表单引擎存储层接口解析器
 *
 * @author clouds
 */
public interface FormEngineRepositoryResolver {

    /**
     * 根据对象获取对象存储层的驱动
     *
     * @param object
     */
    FormEngineRepository resolveRepository(XObject object);
}
