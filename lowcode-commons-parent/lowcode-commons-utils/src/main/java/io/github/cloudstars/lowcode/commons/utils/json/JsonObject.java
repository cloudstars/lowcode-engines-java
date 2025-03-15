package io.github.cloudstars.lowcode.commons.utils.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * JSON对象，封装了FastJson的对象，避免直接让用户依赖FASTJSON对象
 *
 * @author clouds
 */
public class JsonObject implements Map {

    private JSONObject proxy;

    public JsonObject() {
        this.proxy = new JSONObject();
    }

    JsonObject(JSONObject jsonObject) {
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
        return this.proxy.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        Object value = this.proxy.get(key);
        return FastJsonUtils.wrap(value);
    }

    @Override
    public Object put(Object key, Object value) {
        return this.proxy.put(key.toString(), value);
    }

    @Override
    public Object remove(Object key) {
        return this.proxy.remove(key);
    }

    @Override
    public void putAll(Map m) {
        this.proxy.putAll(m);
    }

    @Override
    public void clear() {
        this.proxy.clear();
    }

    @Override
    public Set keySet() {
        return this.proxy.keySet();
    }

    @Override
    public Collection values() {
        return this.proxy.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return this.proxy.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return this.proxy.equals(o);
    }

    @Override
    public int hashCode() {
        return this.proxy.hashCode();
    }
}
