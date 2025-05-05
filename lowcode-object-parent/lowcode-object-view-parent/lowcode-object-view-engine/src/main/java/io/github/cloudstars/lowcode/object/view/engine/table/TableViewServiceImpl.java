package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.engine.table.api.PageQueryOutput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewPageQueryApiInput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewQueryApiInput;

import java.util.List;
import java.util.Map;

/**
 * 表格视图服务实现
 *
 * @author clouds
 */
public class TableViewServiceImpl implements TableViewService {

    @Override
    public PageQueryOutput<Map<String, Object>> execute(String viewKey, TableViewPageQueryApiInput input) {
        ObjectTableView view = this.resolveTableView(viewKey);
        return view.execute(input);
    }

    @Override
    public List<Map<String, Object>> execute(String viewKey, TableViewQueryApiInput input) {
        ObjectTableView view = this.resolveTableView(viewKey);
        return view.execute(input);
    }

    private ObjectTableView resolveTableView(String viewKey) {
        // TODO 根据viewKey生成TablView
        return new ObjectTableViewImpl();
    }

}
