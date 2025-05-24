package io.github.cloudstars.lowcode.value.type;

import java.util.List;

/**
 * 对象数据格式接口
 *
 * @author clouds
 */
public interface XMapValueTypeConfig extends XValueTypeConfig {

    /**
     * 获取对象下的属性列表配置
     *
     * @return 对象下的属性列表
     */
    List<MapPropertyConfig> getProperties();
}
