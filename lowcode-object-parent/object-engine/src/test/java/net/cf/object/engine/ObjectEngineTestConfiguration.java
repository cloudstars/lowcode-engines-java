//package net.cf.object.engine;
//
//import net.cf.commons.test.dataset.IDataSetOperator;
//import net.cf.commons.test.dataset.MysqlDataSetOperator;
//import net.cf.form.repository.mysql.MySQLObjectRepositoryImpl;
//import net.cf.object.engine.def.ObjectTestImpl;
//import net.cf.object.engine.object.ObjectRepositoryProviderTestImpl;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ObjectEngineTestConfiguration {
//
//    @Bean
//    public MySQLObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
//        return new MySQLObjectRepositoryImpl(jdbcTemplate);
//    }
//
//    @Bean
//    public ObjectRepositoryProvider<ObjectTestImpl> objectRepositoryProvider(MySQLObjectRepositoryImpl mySQLObjectRepository) {
//        return new ObjectRepositoryProviderTestImpl(mySQLObjectRepository);
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(NamedParameterJdbcTemplate.class)
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
//        return template;
//    }
//
//    @Bean
//    @ConditionalOnBean(NamedParameterJdbcTemplate.class)
//    public IDataSetOperator initDataLoader(NamedParameterJdbcTemplate jdbcTemplate) {
//        return new MysqlDataSetOperator(jdbcTemplate);
//    }
//
//}
