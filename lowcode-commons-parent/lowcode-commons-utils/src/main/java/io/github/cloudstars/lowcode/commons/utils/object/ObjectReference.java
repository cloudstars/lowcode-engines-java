package io.github.cloudstars.lowcode.commons.utils.object;

public class ObjectReference<T> {

    private T ref;

    public ObjectReference(T ref) {
        this.ref = ref;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
