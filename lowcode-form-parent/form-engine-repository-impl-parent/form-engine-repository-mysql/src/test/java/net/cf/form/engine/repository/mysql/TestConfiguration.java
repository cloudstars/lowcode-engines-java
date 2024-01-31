package net.cf.form.engine.repository.mysql;

import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.StatementExecuteInterceptor;
import net.cf.form.engine.repository.data.DataObjectResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class TestConfiguration {

    @Bean
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
    public DataObjectResolver objectResolver() {
        return new TestDataObjectResolver();
    }

    @Bean
    public StatementExecuteInterceptor interceptor() {
        return new TestStatementExecuteInterceptor();
    }

    @Bean
    public RepositoryDriver driver(StatementExecuteInterceptor interceptor, NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        return new MysqlRepositoryDriverImpl(jdbcTemplate, resolver, Arrays.asList(interceptor));
    }


}
