package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.testcases.statement.select.SelectMainLimitTest;
import net.cf.form.engine.repository.mysql.statement.SqlTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class SelectMainLimitStatementTest implements SelectMainLimitTest {

    private final String SELECT_TRAVEL_LIMIT_SQL = "select APPLY_ID from OQL.TRV_TB where 1 = 1 limit 10";

    private final String SELECT_TRAVEL_LIMIT_OFFSET_SQL = "select APPLY_ID from OQL.TRV_TB where 1 = 1 limit 100, 10";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testSelectTravelLimit() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_LIMIT_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_LIMIT_SQL, sql));
    }

    @Test
    @Override
    public void testSelectTravelLimitOffset() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_LIMIT_OFFSET_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_LIMIT_OFFSET_SQL, sql));
    }
}
