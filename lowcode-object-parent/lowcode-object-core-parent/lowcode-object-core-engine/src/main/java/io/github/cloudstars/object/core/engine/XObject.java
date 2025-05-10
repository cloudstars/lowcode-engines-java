package io.github.cloudstars.object.core.engine;

/**
 * 模型
 *
 * @author clouds
 */
public interface XObject {

    /**
     * 获取主键值
     *
     * @return
     */
    String getId();

    /**
     * 获取某个键的值
     *
     * @param key
     * @return
     */
    Object getValue(String key);

    /**
     * 设置某个键的值
     *
     * @param key
     * @param value
     */
    void setValue(String key, Object value);


}
