
package io.github.cloudstars.lowcode.bpm.commons.config.user;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.assignee.AssigneeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象流程用户节点配置
 *
 * @author clouds
 */
public abstract class AbstractUserTaskNodeConfig extends AbstractNodeConfig {

    /**
     * 任务处理人属性名称
     */
    private static final String ATTR_ASSIGNEE = "assignee";

    /**
     * 任务分派配置
     */
    private AssigneeConfig assignee;


    public AbstractUserTaskNodeConfig() {
    }

    public AbstractUserTaskNodeConfig(JsonObject configJson) {
        super(configJson);

        JsonObject assigneeConfigJson = (JsonObject) configJson.get(ATTR_ASSIGNEE);
        this.assignee = new AssigneeConfig(assigneeConfigJson);
    }

    public AssigneeConfig getAssignee() {
        return assignee;
    }

    public void setAssignee(AssigneeConfig assignee) {
        this.assignee = assignee;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_ASSIGNEE, this.assignee.toJson());
        return configJson;
    }

}
