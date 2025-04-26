package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.editor.JsConfigDescriptorLoader;
import io.github.cloudstars.lowcode.commons.lang.config.XDescriptor;
import io.github.cloudstars.lowcode.commons.lang.config.XDescriptorFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsEditorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        XDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptor.js");
        XDescriptorFactory.register(descriptor);

        XDescriptor descriptorRef = JsConfigDescriptorLoader.loadFromClassPath("descriptors/descriptorRef.js");
        XDescriptorFactory.register(descriptorRef);
    }

}