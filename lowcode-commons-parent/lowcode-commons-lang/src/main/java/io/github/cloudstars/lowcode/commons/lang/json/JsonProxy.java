package io.github.cloudstars.lowcode.commons.lang.json;

import com.alibaba.fastjson.JSON;

/**
 * Json代理类型
 *
 * @param <T> 被代理的JSON对象类型
 */
class JsonProxy<T extends JSON> {

    protected T proxy;

    public String toJsonString() {
        return this.proxy.toString();
    }

}
