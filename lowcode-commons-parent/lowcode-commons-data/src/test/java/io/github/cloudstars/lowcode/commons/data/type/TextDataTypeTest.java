package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.editor.XDescriptorFactory;
import io.github.cloudstars.lowcode.commons.editor.JsConfigDescriptorLoader;
import io.github.cloudstars.lowcode.commons.editor.XDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class TextDataTypeTest {

    @Test
    public void test1() {
        XDescriptor commonsDescriptor = JsConfigDescriptorLoader.loadFromClassPath("type/common.js");
        XDescriptor textDescriptor = JsConfigDescriptorLoader.loadFromClassPath("type/text.js");
        XDescriptorFactory.register(commonsDescriptor);
        XDescriptorFactory.register(textDescriptor);

    }

}
