package net.cf.object.engine.object;

/**
 * 引用字段类型
 *
 * @author clouds
 */
public interface XObjectRefField extends XField {

    /**
     * 获取引用模型的名称
     *
     * @return
     */
    String getRefObjectName();

    /**
     * 是否一对多的引用关系
     *
     * @return
     */
    boolean isMultiRef();

}
