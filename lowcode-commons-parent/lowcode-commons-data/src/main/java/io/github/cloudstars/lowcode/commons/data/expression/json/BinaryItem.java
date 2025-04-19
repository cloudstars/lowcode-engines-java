package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * 二元操作表达式中的左右项
 *
 * @author clouds
 */
public interface BinaryItem extends XConfig {

    // 字段类型
    String TYPE_FIELD = "FIELD";

    // 数据类型
    String TYPE_VALUE = "VALUE";


}
