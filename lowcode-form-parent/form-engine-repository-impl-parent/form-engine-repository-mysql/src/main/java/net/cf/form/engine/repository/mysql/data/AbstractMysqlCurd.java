package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.DataObjectResolver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractMysqlCurd {

    protected NamedParameterJdbcTemplate jdbcTemplate;

    protected DataObjectResolver resolver;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public DataObjectResolver getResolver() {
        return resolver;
    }

    public AbstractMysqlCurd(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        this.jdbcTemplate = jdbcTemplate;
        this.resolver = resolver;
    }
}
