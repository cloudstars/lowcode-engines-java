package io.github.cloudstars.lowcode.object.view.table.config;

import io.github.cloudstars.lowcode.object.commons.config.ObjectConfig;
import io.github.cloudstars.lowcode.object.view.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.table.TableViewActionConfig;
import io.github.cloudstars.lowcode.object.view.table.config.query.QueryConfig;

import java.util.List;

/**
 * 表格视图配置
 *
 * @author clouds
 */
public class TableViewConfig extends AbstractViewConfig {

    /**
     * 模型的配置
     */
    private ObjectConfig objectConfig;

    /**
     * 查询配置
     */
    private QueryConfig queryConfig;

    /**
     * 表头工具栏操作配置
     */
    private List<TableViewActionConfig> toolbarActions;

    /**
     * 行操作配置
     */
    private List<TableViewActionConfig> rowActions;


}
