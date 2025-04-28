package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.formula.engine.FormulaExecutor;
import io.github.cloudstars.lowcode.formula.engine.FormulaExecutorImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormulaEngineTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public FormulaExecutor formulaExecutor() {
        return new FormulaExecutorImpl();
    }

}