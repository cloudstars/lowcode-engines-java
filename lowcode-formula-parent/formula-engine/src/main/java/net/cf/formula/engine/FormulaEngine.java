package net.cf.formula.engine;

/**
 * 公式引擎
 *
 * @author clouds
 */
public interface FormulaEngine {

    /**
     *
     * @param fx 公式
     * @return 公式的计算结果
     */
    Object getValue(String fx);

}
