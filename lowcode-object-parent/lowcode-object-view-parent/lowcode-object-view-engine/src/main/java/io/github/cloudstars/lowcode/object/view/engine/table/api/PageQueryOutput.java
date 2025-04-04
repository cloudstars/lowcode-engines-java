package io.github.cloudstars.lowcode.object.view.engine.table.api;

import java.util.List;

/**
 * 分页查询输出结果
 *
 * @author clouds
 */
public class PageQueryOutput<T extends Object> {

    private int total;

    private List<T> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
