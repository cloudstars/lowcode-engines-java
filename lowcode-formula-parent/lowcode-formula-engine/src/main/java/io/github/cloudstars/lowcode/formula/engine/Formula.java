package io.github.cloudstars.lowcode.formula.engine;

import java.util.Map;

/**
 * 公式
 *
 * @author clouds 
 */
public interface Formula {

    /**
     * 执行公式
     *
     * @return 公式执行结果
     */
    Object execute();

    /**
     * 执行公式
     *
     * @param context 上下文参数
     * @return 公式执行结果
     */
    Object execute(Map<String, Object> context);

}
