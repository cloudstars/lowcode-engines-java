package net.clouds.lowcode.formula;

import net.cf.formula.engine.*;
import net.cf.formula.engine.impl.JuelFormulaEngineImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class TestConfiguration {

    @Bean
    public VariablesValueLoader valueLoader() {
        return new VariableValuesLoader();
    }

    @Bean
    public FormulaEngine engine(VariablesValueLoader valueLoader) {
        JuelFormulaEngineImpl engine = new JuelFormulaEngineImpl(valueLoader);
        Method now, max;
        try {
            now = Functions.class.getMethod("now");
            max = Functions.class.getMethod("max", Number.class, Number.class);
        } catch (NoSuchMethodException e) {
            throw new FastFxException("获取方法失败！", e);
        }

        engine.registerMethod("now", now);
        engine.registerMethod("max", max);

        return engine;
    }
}
