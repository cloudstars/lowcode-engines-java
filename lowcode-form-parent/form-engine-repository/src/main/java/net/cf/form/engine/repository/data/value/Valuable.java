package net.cf.form.engine.repository.data.value;

/**
 * 有值的接口
 *
 * @author clouds
 */
@Deprecated
public interface Valuable<T> {

    /**
     * 获取值
     *
     * @return
     */
    T getValue();

    /**
     * 设置值
     *
     * @param value
     */
    void setValue(T value);
}
