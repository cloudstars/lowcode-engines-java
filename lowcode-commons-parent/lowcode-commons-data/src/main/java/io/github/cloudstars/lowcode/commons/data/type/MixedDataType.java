package io.github.cloudstars.lowcode.commons.data.type;

import java.util.List;
import java.util.Map;

/**
 * 数据格式接口
 *
 * @author clouds
 */
public interface MixedDataType extends DataType {

    /**
     * 获取组合数据格式的属性列表
     *
     * @param options 上下文参数
     * @return 数据属性列表
     */
    List<DataProperty> getProperties(Map<String, Object> options);

}
