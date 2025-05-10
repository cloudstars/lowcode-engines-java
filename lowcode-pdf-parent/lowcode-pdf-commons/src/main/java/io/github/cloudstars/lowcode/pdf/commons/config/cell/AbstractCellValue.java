package io.github.cloudstars.lowcode.pdf.commons.config.cell;

public abstract class AbstractCellValue implements XCellValue {

    private XCellValueConfig config;

    public AbstractCellValue(XCellValueConfig config) {
        this.config = config;
    }
}
