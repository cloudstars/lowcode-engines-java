package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.lang.enums.IEnum;

/**
 * 纸张大小
 *
 * @author clouds
 */
public enum PageSizeEnum implements IEnum {

    A3("A3"),
    A4("A4"),
    A5("A5");

    private String code;

    PageSizeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
