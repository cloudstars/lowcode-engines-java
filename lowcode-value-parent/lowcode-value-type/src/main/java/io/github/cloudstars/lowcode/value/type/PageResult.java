package io.github.cloudstars.lowcode.value.type;

import java.util.List;

/**
 * 分页结果
 *
 * @param <T>
 */
public class PageResult<T extends Object> {

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
