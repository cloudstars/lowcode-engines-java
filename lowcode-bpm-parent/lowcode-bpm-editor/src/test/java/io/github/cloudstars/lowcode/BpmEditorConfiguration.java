package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.editor.parser.ProcessConfigParser;
import io.github.cloudstars.lowcode.commons.editor.XDescriptorFactory;
import io.github.cloudstars.lowcode.commons.editor.JsConfigDescriptorLoader;
import io.github.cloudstars.lowcode.commons.editor.XDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmEditorConfiguration implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(BpmEditorConfiguration.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        XDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath("bpm-descriptor.js");
        XDescriptorFactory.register(descriptor);
        logger.info("BPM规范注册成功");
    }

    @Bean
    public ProcessConfigParser processConfigParser() {
        return new ProcessConfigParser();
    }

}