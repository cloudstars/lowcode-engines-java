package io.github.cloudstars.lowcode.expression.enums;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2025/4/21
 */
public enum VariableDeclarationEnums {
    OBJECT(1, "object"),
    ARRAY(2, "array");

    private final int code;

    private final String name;

    VariableDeclarationEnums(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
