package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.ObjectView;
import io.github.cloudstars.lowcode.object.view.table.config.TableViewConfig;

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
    JsonObject toRenderSchema(TableViewConfig viewConfig);

}
