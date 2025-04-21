package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.commons.value.XValue;

/**
 * 文件格式的数据
 *
 * @author clouds
 */
public class FileObject implements XValue {

    private String key;

    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
