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
    private long total;

    /**
     * 当前页的数据
     */
    private List<T> list;

    public PageResult() {
    }

    public PageResult(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
