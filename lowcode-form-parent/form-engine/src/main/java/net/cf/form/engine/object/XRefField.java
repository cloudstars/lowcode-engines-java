package net.cf.form.engine.object;

/**
 * 引用字段类型
 *
 * @author clouds
 */
public interface XRefField extends XField {

    /**
     * 获取模型引用的类型
     *
     * @return
     */
    ObjectRefType getRefType();

    /**
     * 获取关联对象的名称
     *
     * @return
     */
    String getRefObjectCode();

}
