package net.cf.form.engine.object;

/**
 * 字段的个性化属性
 *
 * @author clouds
 */
public interface XFieldAttribute {

    /**
     * 获取个性化属性归属的字段
     *
     * @return
     * @param <T>
     */
    <T extends XField> T getOwner();

    /**
     * 获取配置的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取配置的编号
     *
     * @return
     */
    String getCode();

    /**
     * 获取配置的值
     *
     * @return
     */
    Object getValue();
}
