package net.cf.form.engine.repository.mongo;

import net.cf.form.engine.repository.data.DataObjectResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public DataObjectResolver objectResolver() {
        return new TestDataObjectResolver();
    }

}
