package io.github.cloudstars.lowcode.commons.utils.json;

import com.alibaba.fastjson.JSON;

class Json<T extends JSON> {

    protected T proxy;

    public String toJsonString() {
        return this.proxy.toString();
    }

}
