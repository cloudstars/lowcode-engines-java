package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;

import java.util.List;

/**
 * PDF生成配置
 *
 * @author clouds
 */
public class PdfBuildConfig extends AbstractResourceConfig {

    /**
     * 纸张大小
     */
    private PageSizeEnum pageSize;

    /**
     * 栏位数
     */
    private Integer columnSize;

    /**
     * PDF元素列表
     */
    private List<XElementConfig> elements;

    public PageSizeEnum getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSizeEnum pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public List<XElementConfig> getElements() {
        return elements;
    }

    public void setElements(List<XElementConfig> elements) {
        this.elements = elements;
    }

}
