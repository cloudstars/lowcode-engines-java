package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.editor.visit.ProcessConfigParser;
import org.springframework.context.annotation.Bean;

public abstract class AbstractBpmEngineTestConfiguration {

    @Bean
    public ProcessConfigParser processConfigParser() {
        return new ProcessConfigParser();
    }

}
