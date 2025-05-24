package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.formula.engine.Formula;
import io.github.cloudstars.lowcode.formula.engine.FormulaExecutor;
import io.github.cloudstars.lowcode.formula.engine.FormulaExecutorImpl;
import io.github.cloudstars.lowcode.predicate.type.FormulaPredicateConfig;

import java.util.Map;

/**
 * 公式断言
 *
 * @author clouds 
 */
public class FormulaPredicate extends AbstractPredicate<FormulaPredicateConfig> {

    private FormulaExecutor executor = new FormulaExecutorImpl();

    private Formula formula;

    public FormulaPredicate(FormulaPredicateConfig config) {
        super(config);

        this.formula = this.executor.compile(config.getFormula());
    }

    @Override
    public boolean test(Map<String, Object> paramMap) {
        Object result = this.formula.execute(paramMap);
        return super.parseResult(result);
    }

}
