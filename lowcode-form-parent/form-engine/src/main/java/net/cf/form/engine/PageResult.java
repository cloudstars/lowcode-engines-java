package net.cf.form.engine;

import java.util.List;
import java.util.Map;

/**
 * 分页查询结果
 *
 * @author clouds
 */
@Deprecated
public class PageResult {

    private int total;

    private List<Map<String, Object>> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
