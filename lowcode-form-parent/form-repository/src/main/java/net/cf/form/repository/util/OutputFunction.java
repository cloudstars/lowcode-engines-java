package net.cf.form.repository.util;

@FunctionalInterface
public interface OutputFunction<T> {

    void print(T item);
}
