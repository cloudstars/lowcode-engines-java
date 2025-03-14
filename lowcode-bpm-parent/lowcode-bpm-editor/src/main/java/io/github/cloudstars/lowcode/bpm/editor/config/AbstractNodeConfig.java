package io.github.cloudstars.lowcode.bpm.editor.config;

/**
 * 节点配置
 *
 * @author clouds
 */
public abstract class AbstractNodeConfig {

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
