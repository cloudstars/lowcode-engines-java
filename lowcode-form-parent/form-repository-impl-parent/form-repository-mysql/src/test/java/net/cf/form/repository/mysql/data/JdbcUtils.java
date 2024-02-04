package net.cf.form.repository.mysql.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类
 *
 * @author clouds
 */
@Component
public class JdbcUtils {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询列表数据
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql, RowMapper rowMapper) {
        List<Map<String, Object>> resultList = jdbcTemplate.query(sql, rowMapper);
        return resultList;
    }

}
