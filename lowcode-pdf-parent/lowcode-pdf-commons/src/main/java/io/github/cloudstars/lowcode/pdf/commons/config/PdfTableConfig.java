package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * PDF表格配置
 *
 * @author clouds
 */
public class PdfTableConfig extends AbstractConfig {

    /**
     * 列数
     */
    private int columSize;

    /**
     * 每一行配置
     */
    private List<PdfRowConfig> rows;

    public PdfTableConfig() {
    }

    public PdfTableConfig(JsonObject configJson) {
        super(configJson);
    }


    public int getColumSize() {
        return columSize;
    }

    public void setColumSize(int columSize) {
        this.columSize = columSize;
    }

    public List<PdfRowConfig> getRows() {
        return rows;
    }

    public void setRows(List<PdfRowConfig> rows) {
        this.rows = rows;
    }
}
