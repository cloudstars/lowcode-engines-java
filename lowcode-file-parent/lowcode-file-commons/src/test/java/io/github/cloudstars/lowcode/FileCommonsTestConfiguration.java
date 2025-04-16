package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.type.ValueTypeConfigClassFactory;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeParser;
import io.github.cloudstars.lowcode.file.commons.FileValueTypeConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileCommonsTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueTypeConfigClassFactory.register(FileValueTypeConfig.class);
    }

    @Bean
    public DataTypeParser valueTypeParser() {
        return new DataTypeParser();
    }
    
}