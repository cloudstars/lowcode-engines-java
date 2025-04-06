package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfigClassFactory;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeParser;
import io.github.cloudstars.lowcode.file.commons.FileDataTypeConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileCommonsTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataTypeConfigClassFactory.register(FileDataTypeConfig.class);
    }

    @Bean
    public DataTypeParser valueTypeParser() {
        return new DataTypeParser();
    }
    
}