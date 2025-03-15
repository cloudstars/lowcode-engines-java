package io.github.cloudstars.lowcode.commons.utils.json;

import com.alibaba.fastjson.JSONArray;

import java.util.*;
import java.util.function.Consumer;

public class JsonArray extends Json<JSONArray> implements List<Object> {

    public JsonArray() {
        this.proxy = new JSONArray();
    }

    JsonArray(JSONArray jsonArray) {
        assert (jsonArray != null);
        this.proxy = jsonArray;
    }

    @Override
    public int size() {
        return this.proxy.size();
    }

    @Override
    public boolean isEmpty() {
        return this.proxy.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.proxy.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return this.proxy.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.proxy.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.proxy.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        return this.proxy.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return this.proxy.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.proxy.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return this.proxy.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        return this.proxy.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.proxy.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.proxy.retainAll(c);
    }

    @Override
    public void clear() {
        this.proxy.clear();
    }

    @Override
    public Object get(int index) {
        Object value = this.proxy.get(index);
        return FastJsonUtils.wrap(value);
    }

    @Override
    public Object set(int index, Object element) {
        return this.proxy.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        this.proxy.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return this.proxy.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.proxy.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.proxy.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return this.proxy.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return this.proxy.listIterator();
    }

    @Override
    public void forEach(Consumer<? super Object> action) {
        Objects.requireNonNull(action);
        for (Object t : this) {
            action.accept(FastJsonUtils.wrap(t));
        }
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return this.proxy.subList(fromIndex, toIndex);
    }
}
