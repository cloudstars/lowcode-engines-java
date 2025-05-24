package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.descriptor.XConfigDescriptor;
import io.github.cloudstars.lowcode.commons.descriptor.ConfigDescriptorFactory;
import io.github.cloudstars.lowcode.commons.descriptor.js.JsConfigDescriptorLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsDescriptorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        XConfigDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptor.js");
        ConfigDescriptorFactory.register(descriptor);

        XConfigDescriptor descriptorRef = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptorRef.js");
        ConfigDescriptorFactory.register(descriptorRef);
    }

}