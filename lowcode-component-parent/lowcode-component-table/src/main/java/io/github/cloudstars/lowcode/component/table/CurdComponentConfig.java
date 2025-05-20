package io.github.cloudstars.lowcode.component.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 数据表格组件配置
 *
 * @author clouds
 */
public class CurdComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public CurdComponentConfig() {
    }

    public CurdComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
