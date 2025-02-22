package net.cf.object.engine.data;

import java.util.Map;

/**
 * 查询结果转换器
 *
 * @author
 */
public interface ResultReducer {

    /**
     * 将驱动层的查询结果转换为引擎层的数据格式
     *
     * @param dataMap
     * @return
     */
    Map<String, Object> reduceResult(Map<String, Object> dataMap);
}
