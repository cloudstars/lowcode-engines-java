package net.cf.object.engine.data;

/**
 * 分页查询请求
 *
 * @author clouds
 */
public class PageRequest {

    /**
     * 当前页的页码（从0开始）
     */
    private int index;

    /**
     * 当前页的大小
     */
    private int size;

    public PageRequest() {
    }

    public PageRequest(int index, int size) {
        this.index = index;
        this.size = size;
    }

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
