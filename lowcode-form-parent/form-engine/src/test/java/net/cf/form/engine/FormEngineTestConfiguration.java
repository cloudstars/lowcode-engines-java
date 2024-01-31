package net.cf.form.engine;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FormEngineTestConfiguration {

    /*@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template;
    }

    @Bean
    public DataObjectResolver objectResolver(XObjectResolver objectResolver) {
        return new DataObjectResolverImpl(objectResolver);
    }

    @Bean
    public RepositoryDriver driver(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataObjectResolver resolver) {
        return new MysqlRepositoryDriverImpl(namedParameterJdbcTemplate, resolver);
    }

    @Bean
    public XObjectResolver resolver() {
        return new TestObjectResolver();
    }

    @Bean
    public ObjectRecordEngine recordEngine(RepositoryDriver driver, XObjectResolver resolver) {
        ObjectRecordEngineImpl objectRecordEngine =  new ObjectRecordEngineImpl(driver, resolver);
        objectRecordEngine.setRecordResolver(new TestObjectRecordResolver());
        return objectRecordEngine;
    }*/
}
