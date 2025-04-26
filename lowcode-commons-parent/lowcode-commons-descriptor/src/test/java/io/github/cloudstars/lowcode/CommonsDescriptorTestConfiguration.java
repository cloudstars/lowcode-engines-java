package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.descriptor.JsConfigDescriptorLoader;
import io.github.cloudstars.lowcode.commons.descriptor.XDescriptor;
import io.github.cloudstars.lowcode.commons.descriptor.XDescriptorFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsDescriptorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        XDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptor.js");
        XDescriptorFactory.register(descriptor);

        XDescriptor descriptorRef = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptorRef.js");
        XDescriptorFactory.register(descriptorRef);
    }

}