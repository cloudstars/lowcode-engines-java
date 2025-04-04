package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.engine.ObjectView;
import io.github.cloudstars.lowcode.object.view.commons.config.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.table.api.PageQueryOutput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewPageQueryApiInput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewQueryApiInput;

import java.util.List;
import java.util.Map;

/**
 * 模型表格视图接口
 *
 * @author clouds 
 */
public interface TableView<C extends AbstractViewConfig> extends ObjectView<C> {

    /**
     * 执行表格视图分页查询接口
     *
     * @param input
     * @return 分页查询结果
     */
    PageQueryOutput<Map<String, Object>> execute(TableViewPageQueryApiInput input);

    /**
     * 执行表格视图查询接口
     *
     * @param input
     * @return 查询结果
     */
    List<Map<String, Object>> execute(TableViewQueryApiInput input);

}
