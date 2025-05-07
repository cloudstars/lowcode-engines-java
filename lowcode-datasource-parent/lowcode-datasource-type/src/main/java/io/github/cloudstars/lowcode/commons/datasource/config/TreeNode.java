package io.github.cloudstars.lowcode.commons.datasource.config;

import java.util.List;

/**
 * 树型数据格式的数据
 *
 * @author clouds
 */
public class TreeNode {

    /**
     * 标识
     */
    private String key;

    /**
     * 标签
     */
    private String label;

    /**
     * 儿子节点列表
     */
    private List<TreeNode> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
