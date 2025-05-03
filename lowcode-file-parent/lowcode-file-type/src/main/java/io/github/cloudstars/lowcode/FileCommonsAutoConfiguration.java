package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.value.*;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigClassFactory;
import io.github.cloudstars.lowcode.file.commons.FileValueTypeConfig;
import io.github.cloudstars.lowcode.file.commons.FileValueTypeImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class FileCommonsAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueTypeConfigClassFactory.register(FileValueTypeConfig.class);
        ValueTypeClassFactory.register(FileValueTypeImpl.class);
    }

}
