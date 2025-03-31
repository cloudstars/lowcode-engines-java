package io.github.cloudstars.lowcode.commons.lang.util;

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
