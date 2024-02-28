package net.cf.object.engine;

import net.cf.form.repository.mysql.MySQLObjectRepositoryImpl;
import net.cf.object.engine.def.ObjectTestImpl;
import net.cf.object.engine.object.ObjectRepositoryProviderTestImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ObjectEngineTestConfiguration {

    @Bean
    public MySQLObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySQLObjectRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public ObjectRepositoryProvider<ObjectTestImpl> objectRepositoryProvider(MySQLObjectRepositoryImpl mySQLObjectRepository) {
        return new ObjectRepositoryProviderTestImpl(mySQLObjectRepository);
    }

}
