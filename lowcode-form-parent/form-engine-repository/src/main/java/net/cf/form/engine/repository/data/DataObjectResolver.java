package net.cf.form.engine.repository.data;

/**
 * 数据对象解析器
 *
 * @author clouds
 */
@Deprecated
public interface DataObjectResolver {

    /**
     * 根据对象名称解析一个数据对象
     *
     * @param objectName 对象的名称
     * @return
     */
    DataObject resolveObject(String objectName);
    
}
