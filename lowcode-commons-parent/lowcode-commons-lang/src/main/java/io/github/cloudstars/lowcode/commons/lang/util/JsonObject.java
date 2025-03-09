package io.github.cloudstars.lowcode.commons.lang.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class JsonObject implements Map {

    private JSONObject proxy;

    public JsonObject(JSONObject jsonObject) {
        assert (jsonObject != null);
        this.proxy = jsonObject;
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
    public boolean containsKey(Object key) {
        return this.proxy.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
