package io.github.cloudstars.lowcode.bpm.commons.config.loop;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.condition.ConditionConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 循环节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.LOOP")
public class LoopNodeConfig extends AbstractNodeConfig {

    private static final String ATTR_ITEMS_VAR_NAME = "";
    private static final String ATTR_ITEM_VAR_NAME = "";

    /**
     * 循环列表变量名称
     */
    private String itemsVarName;

    /**
     * 循环变量名称
     */
    private String itemVarName;

    /**
     * 循环条件
     */
    private ConditionConfig condition;

    /**
     * 循环分支
     */
    private BranchNodeConfig branch;


    public LoopNodeConfig() {
    }

    public LoopNodeConfig(JsonObject configJson) {
        super(configJson);

        this.itemsVarName = (String) configJson.get(ATTR_ITEMS_VAR_NAME);
        this.itemVarName = (String) configJson.get(ATTR_ITEM_VAR_NAME);
        this.condition = new ConditionConfig((JsonObject) configJson.get(ATTR_CONDITION));
        this.branch = new BranchNodeConfig((JsonObject) configJson.get(ATTR_BRANCH));
    }


    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.LOOP;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_ITEMS_VAR_NAME, this.itemsVarName);
        configJson.put(ATTR_ITEM_VAR_NAME, this.itemVarName);
        configJson.put(ATTR_CONDITION, this.condition.toJson());
        configJson.put(ATTR_BRANCH, this.branch.toJson());

        return configJson;
    }

}
