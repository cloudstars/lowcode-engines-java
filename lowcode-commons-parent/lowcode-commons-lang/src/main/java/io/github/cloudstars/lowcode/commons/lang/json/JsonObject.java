package io.github.cloudstars.lowcode.commons.lang.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * JSON对象，封装了FastJson的对象，避免直接让用户依赖FASTJSON对象
 *
 * @author clouds
 */
public class JsonObject<K, V> extends JsonProxy<JSONObject> implements Map<String, Object> {

    public JsonObject() {
        this.proxy = new JSONObject();
    }

    public JsonObject(Map<String, Object> dataMap) {
        this.proxy = new JSONObject(dataMap);
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
    public Object getOrDefault(Object key, Object defaultValue) {
        Object value = this.proxy.getOrDefault(key, defaultValue);
        return FastJsonUtils.wrap(value);
    }

    @Override
    public Object put(String key, Object value) {
        return this.proxy.put(key, value);
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

    public String toJsonString() {
        return this.proxy.toString();
    }

    /**
     * 从JSON对象中获取一个key的值，如果key存在，则调用转换函数转换成正确的格式，否则返回默认值
     *
     * @param key 属性名
     * @param getter 取值函数
     * @param defaultValue 默认值
     * @return 期望的值
     * @param <T> 期望的类型
     */
    public <T extends Object> T getNonNullOrDefault(String key, GetterFunction<T> getter, T defaultValue) {
        Object value = this.get(key);
        if (value != null) {
            return getter.get(value);
        } else {
            return defaultValue;
        }
    }

    public interface GetterFunction<T extends Object> {
        T get(Object nonNullValue);
    }

}
