package io.github.cloudstars.lowcode.formula.engine;

/**
 * 公式引擎
 *
 * @author clouds
 */
public class FormulaEngine {

    /**
     * 编译
     *
     * @param fx
     * @return
     */
    public static Formula compile(String fx) {
        return null;
    }

    /**
     * 执行一个公式
     *
     * @param fx
     * @return
     */
    public static Object execute(String fx) {
        Formula formula = compile(fx);
        return formula.execute();
    }

}
