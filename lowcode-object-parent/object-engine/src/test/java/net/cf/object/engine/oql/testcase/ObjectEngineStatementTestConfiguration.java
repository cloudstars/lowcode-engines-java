package net.cf.object.engine.oql.testcase;

import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.oql.parser.XObjectResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectEngineStatementTestConfiguration {

    @Bean
    public XObjectResolver objectResolver() {
        return new TestObjectResolver();
    }

}
