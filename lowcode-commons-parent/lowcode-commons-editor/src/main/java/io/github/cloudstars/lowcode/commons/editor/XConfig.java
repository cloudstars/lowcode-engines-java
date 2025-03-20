package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置
 *
 * @author clouds
 */
public interface XConfig {

    /**
     * 将配置转为 JSON 字符串
     *
     * @return
     */
    JsonObject toJson();

}
