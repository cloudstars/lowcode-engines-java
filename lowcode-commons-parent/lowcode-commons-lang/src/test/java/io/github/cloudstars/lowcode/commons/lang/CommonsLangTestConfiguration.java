package io.github.cloudstars.lowcode.commons.lang;

import io.github.cloudstars.lowcode.commons.lang.config.Descriptor;
import io.github.cloudstars.lowcode.commons.lang.config.XDescriptorFactory;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsLangTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String desJsonString = FileUtils.loadTextFromClasspath("config/descriptor/descriptor.json");
        Descriptor descriptor = JsonUtils.parseObject(desJsonString, Descriptor.class);
        XDescriptorFactory.register(descriptor);

        String desRefJsonString = FileUtils.loadTextFromClasspath("config/descriptor/descriptorRef.json");
        Descriptor descriptorRef = JsonUtils.parseObject(desRefJsonString, Descriptor.class);
        XDescriptorFactory.register(descriptorRef);
    }

}