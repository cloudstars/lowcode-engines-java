package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.editor.table.ObjectCrudTableViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.ObjectViewApi;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewQueryApi;

public class ObjectCurdTableViewImpl extends AbstractObjectTableViewImpl<ObjectCrudTableViewConfig> {

    public ObjectCurdTableViewImpl(ObjectCrudTableViewConfig viewConfig) {
        super(viewConfig);
    }

    @Override
    protected ObjectViewApi resolveApi(String apiType) {
        return null;
    }

    /*private List<Map<String, Object>> execute(TableViewQueryApiInput input) {
        TableViewQueryApi api = this.loadTableViewQueryApi();
        return api.execute(input);
    }*/


    /**
     * 执行表格视图分页查询接口
     *
     * @param input
     * @return 分页查询结果
     */
    //PageQueryOutput<Map<String, Object>> execute(TableViewPageQueryApiInput input);

    /**
     * 执行表格视图查询接口
     *
     * @param input
     * @return 查询结果
     */
    //List<Map<String, Object>> execute(TableViewQueryApiInput input);


    /**
     * 加载表格视图查询接口
     *
     * @return
     */
    private TableViewQueryApi loadTableViewQueryApi() {
        return null;
    }

    /*public PageQueryOutput<Map<String, Object>> execute(TableViewPageQueryApiInput input) {
        TableViewPageQueryApi api = this.loadTableViewPageQueryApi();
        return api.execute(input);
    }*/


    /**
     * 加载表格视图分页查询接口
     *
     * @return
     */
    /*private TableViewPageQueryApi loadTableViewPageQueryApi() {
        return null;
    }*/

}
