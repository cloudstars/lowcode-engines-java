package net.cf.object.engine.mysql;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mysql.MySqlObjectRepositoryImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
public class ObjectEngineMySqlAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectRepository objectRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate template) {
        return new MySqlObjectRepositoryImpl(jdbcTemplate, template);
    }

}
