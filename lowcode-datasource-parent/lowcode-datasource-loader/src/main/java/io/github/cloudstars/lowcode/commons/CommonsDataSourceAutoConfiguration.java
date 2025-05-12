package io.github.cloudstars.lowcode.commons;

import io.github.cloudstars.lowcode.commons.datasource.DataSourceLoaderClassFactory;
import io.github.cloudstars.lowcode.commons.datasource.StaticDataSourceLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataSourceAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据源加载器类型
        DataSourceLoaderClassFactory.register(StaticDataSourceLoader.class);
    }

}
