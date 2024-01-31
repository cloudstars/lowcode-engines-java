package net.cf.form.engine.repository.util;

@FunctionalInterface
public interface OutputFunction<T> {

    void print(T item);
}
