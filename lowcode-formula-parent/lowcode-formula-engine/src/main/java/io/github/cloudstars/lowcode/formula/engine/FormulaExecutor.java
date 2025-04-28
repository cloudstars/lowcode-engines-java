package io.github.cloudstars.lowcode.formula.engine;

import java.util.Map;

/**
 * 公式执行器
 *
 * @author clouds
 */
public interface FormulaExecutor {

    /**
     * 编译
     *
     * @param fx
     * @return
     */
    Formula compile(String fx);

    /**
     * 执行一个公式
     *
     * @param fx
     * @return 执行结果
     */
    Object execute(String fx);

    /**
     * 执行一个公式
     *
     * @param fx
     * @param context 上下文参数
     * @return 执行结果
     */
    Object execute(String fx, Map<String, Object> context);

}
