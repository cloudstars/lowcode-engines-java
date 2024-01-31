package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.testcases.statement.select.SelectMainSimpleWhereTest;
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
public class SelectMainSimpleWhereStatementTest implements SelectMainSimpleWhereTest {

    private static final String SELECT_TRAVEL_TRUE_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_TB where 1 = 1";

    private static final String SELECT_TRAVEL_IN_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_TB where APPLY_ID in (1, 2)";

    private static final String SELECT_TRAVEL_LIKE_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_TB where REASON like '%出差%'";

    private static final String SELECT_TRAVEL_COMPARE_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_TB where (START_DATE >= '2023-06-07' and START_DATE <= '2023-06-15') or (END_DATE >= '2023-06-10' and END_DATE <= '2023-06-20')";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testSelectTravelTrue() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_TRUE_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_TRUE_SQL, sql));
    }

    @Test
    @Override
    public void testSelectTravelIn() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_IN_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_IN_SQL, sql));
    }

    @Test
    @Override
    public void testSelectTravelLike() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_LIKE_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_LIKE_SQL, sql));
    }

    @Test
    @Override
    public void testTravelCompare() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_COMPARE_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_COMPARE_SQL, sql));
    }

}
