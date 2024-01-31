package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.mysql.statement.delete.DeleteSqlBuilder;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class MysqlDeleteImpl extends AbstractMysqlCurd {

    public MysqlDeleteImpl(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        super(jdbcTemplate, resolver);
    }

    public int deleteSelf(DeleteSqlInfo deleteSqlInfo) {
        String sql = new DeleteSqlBuilder(deleteSqlInfo).getSql();
        int effectedRows = jdbcTemplate.update(sql, (Map) null);

        return effectedRows;
    }

    public int deleteSelf(DeleteSqlInfo deleteSqlInfo, Map<String, Object> paramMap) {
        String sql = new DeleteSqlBuilder(deleteSqlInfo).getSql();
        int effectedRows = jdbcTemplate.update(sql, paramMap);

        return effectedRows;
    }


}
