package io.github.cloudstars.lowcode.commons;

import io.github.cloudstars.lowcode.commons.datasource.config.DataSourceConfigClassFactory;
import io.github.cloudstars.lowcode.commons.datasource.config.ExpressionDataSourceConfig;
import io.github.cloudstars.lowcode.commons.datasource.config.StaticDataSourceConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataSourceAutoConfiguration  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的DataSource配置类型
        DataSourceConfigClassFactory.register(StaticDataSourceConfig.class);
        DataSourceConfigClassFactory.register(ExpressionDataSourceConfig.class);
    }

}
