package io.github.cloudstars.lowcode.commons.lang.enums;

import io.github.cloudstars.lowcode.commons.lang.util.StringUtils;

public class EnumUtils {

    /**
     * 根据code返回对应的枚举值
     *
     * @param code
     * @return
     * @param <T>
     */
    public static <T extends IEnum> T of(Class<T> tClass, String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        T[] enumConstants = tClass.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if (enumConstant.getCode().equalsIgnoreCase(code)) {
                return enumConstant;
            }
        }

        return null;
    }

}
