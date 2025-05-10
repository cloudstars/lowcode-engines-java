package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;

/**
 * PDF生成配置
 *
 * @author clouds
 */
public class PdfBuildConfig extends AbstractResourceConfig {

    /**
     * 纸张大小
     */
    private PaperSize paperSize;

    /**
     * 表格配置
     */
    private PdfTableConfig table;

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
    }

    public PdfTableConfig getTable() {
        return table;
    }

    public void setTable(PdfTableConfig table) {
        this.table = table;
    }
}
