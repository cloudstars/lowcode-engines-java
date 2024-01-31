package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.testcases.statement.select.SelectAggregateTest;
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
public class SelectAggregateStatementTest implements SelectAggregateTest {

    /**
     * Count主差对象生成的SQL
     */
    private final String COUNT_TRAVEL_SQL = "select count(*), count(1), count(0) from OQL.TRV_TB";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testCountTravel() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(COUNT_TRAVEL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(COUNT_TRAVEL_SQL, sql));
    }
}