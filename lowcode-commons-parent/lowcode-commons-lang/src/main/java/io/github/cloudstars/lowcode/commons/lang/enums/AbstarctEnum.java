package io.github.cloudstars.lowcode.commons.lang.enums;

public abstract class AbstarctEnum implements IEnum {

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
