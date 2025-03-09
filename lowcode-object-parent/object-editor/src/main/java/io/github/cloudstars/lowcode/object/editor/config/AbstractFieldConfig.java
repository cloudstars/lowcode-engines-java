package io.github.cloudstars.lowcode.object.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;

public class AbstractFieldConfig implements XConfig {

    private String key;

    private String code;

    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toJsonString() {
        return null;
    }

}
