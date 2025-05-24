package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.text.TextElementConfig;

import java.util.List;

/**
 * PDF生成配置
 *
 * @author clouds
 */
public class PdfBuildConfig extends AbstractResourceConfig {

    // 纸张大小
    private static final String ATTR_PAGE_SIZE = "pageSize";

    // 分栏数
    private static final String ATTR_COLUMN_SIZE = "columnSize";

    // 元素列表
    private static final String ATTR_ELEMENTS = "elements";

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


    public PdfBuildConfig() {
    }

    public PdfBuildConfig(JsonObject configJson) {
        super(configJson);

        this.pageSize = ConfigUtils.getEnum(configJson, ATTR_PAGE_SIZE, PageSizeEnum.class);
        this.columnSize = ConfigUtils.getInteger(configJson, ATTR_COLUMN_SIZE);
        this.elements = ConfigUtils.getList(configJson, ATTR_ELEMENTS, (v) -> new TextElementConfig(v));
    }

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

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_PAGE_SIZE, this.pageSize);
        ConfigUtils.put(configJson, ATTR_COLUMN_SIZE, this.columnSize);
        ConfigUtils.putJsonArray(configJson, ATTR_ELEMENTS, this.elements);

        return configJson;
    }

}
