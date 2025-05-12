package io.github.cloudstars.lowcode.commons.lang.enums;

/**
 * 有代码的枚举值
 *
 * @author clouds
 */
public interface IEnum {

    /**
     * 获取枚举值的代码
     *
     * @return 代码
     */
    String getCode();

    /**
     * 获取枚举值的描述
     *
     * @return 描述
     */
    default String getDescription() {
        return null;
    }

}
