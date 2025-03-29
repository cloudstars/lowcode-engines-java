package io.github.cloudstars.lowcode.object.view.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的视图配置类型
 *
 * @author clouds
 */
public abstract class AbstractViewConfig extends AbstractConfig implements XConfig {

    /**
     * 视图的类型
     */
    private String viewType;

    /**
     * 视图的名称
     */
    private String viewName;

    /**
     * 视图归属的模型名称
     */
    private String objectName;

    public AbstractViewConfig() {
    }

    public AbstractViewConfig(JsonObject configJson) {
        this.setViewType((String) configJson.get("viewType"));
        this.setViewName((String) configJson.get("viewName"));
        this.setObjectName((String) configJson.get("objectName"));
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        jsonObject.put("type", this.viewType);
        jsonObject.put("name", this.viewName);
        jsonObject.put("objectName", this.objectName);

        return jsonObject;
    }

}
