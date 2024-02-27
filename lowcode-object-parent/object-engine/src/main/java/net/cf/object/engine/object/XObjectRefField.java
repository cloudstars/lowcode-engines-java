package net.cf.object.engine.object;

/**
 * 引用字段类型
 *
 * @author clouds
 */
public interface XObjectRefField extends XField {

    /**
     * 获取关联模型的名称
     *
     * @return
     */
    XObject getRefObject();

    /**
     * 是否一对多
     *
     * @return
     */
    boolean isMultiRef();

}
