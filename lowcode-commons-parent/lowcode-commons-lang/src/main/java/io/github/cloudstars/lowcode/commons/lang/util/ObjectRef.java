package io.github.cloudstars.lowcode.commons.lang.util;

/**
 * 对象引用
 *
 * @author clouds
 * @param <T> 被引用的对象
 */
public class ObjectRef<T> {

    private T ref;

    public ObjectRef(T ref) {
        this.ref = ref;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
