package io.github.cloudstars.lowcode.object.view;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 模型表格视图接口
 *
 * @author clouds 
 */
public interface ObjectTableView extends ObjectView {

    /**
     * 转换为渲染的配置
     *
     * @return
     */
    JsonObject toRenderSchema(TableMode mode);

}
