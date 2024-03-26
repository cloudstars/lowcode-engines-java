package net.cf.object.engine.data;

import java.util.Map;

/**
 * 参数转换器
 *
 * @author
 */
public interface ParameterMapper {

    /**
     * 将引擎层的参数转换为驱动层的参数
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> mapParameter(Map<String, Object> paramMap);
}
