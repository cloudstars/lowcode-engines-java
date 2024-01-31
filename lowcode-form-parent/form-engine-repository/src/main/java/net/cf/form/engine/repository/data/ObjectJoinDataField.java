package net.cf.form.engine.repository.data;

/**
 * 对象关联字段（子对象、主对象、相关对象）
 *
 * @author clouds
 */
@Deprecated
public interface ObjectJoinDataField extends IDataField {

    /**
     * 获取关联的类型
     *
     * @return
     */
    JoinType getJoinType();

    /**
     * 获取关联的对象名称
     *
     * @return
     */
    String getJoinObjectName();
}
