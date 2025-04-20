package io.github.cloudstars.lowcode.bpm.commons.config.loop;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 循环节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.LOOP")
public class LoopBranchNodeConfig extends BranchNodeConfig {

    // 循环列表配置名称
    private static final String ATTR_ITEMS = "items";

    // 当前迭代元素配置名称
    private static final String ATTR_ITEM = "item";

    /**
     * 循环列表变量名称
     */
    private String items;

    /**
     * 循环变量名称
     */
    private String item;

    public LoopBranchNodeConfig() {
    }

    public LoopBranchNodeConfig(JsonObject configJson) {
        super(configJson);

        this.items = (String) configJson.get(ATTR_ITEMS);
        this.item = (String) configJson.get(ATTR_ITEM);
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.LOOP;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            super.accept(visitor);
            visitor.endVisit(this);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_ITEMS, this.items);
        configJson.put(ATTR_ITEM, this.item);

        return configJson;
    }

}
