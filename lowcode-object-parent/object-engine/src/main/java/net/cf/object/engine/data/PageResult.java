package net.cf.object.engine.data;

import java.util.List;

/**
 * 分页查询结果
 *
 * @author clouds
 */
public class PageResult<T> {

    /**
     * 总条数
     */
    private int total;

    /**
     * 当前页的数据
     */
    private List<T> list;

    public PageResult() {
    }

    public PageResult(int total, List<T> list) {
        this.total = total;
        this.list = list;
    }

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
