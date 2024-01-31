package net.cf.form.engine;

/**
 * 分页查询结果
 *
 * @author clouds
 */
@Deprecated
public class Page {

    private int index;

    private int size;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
