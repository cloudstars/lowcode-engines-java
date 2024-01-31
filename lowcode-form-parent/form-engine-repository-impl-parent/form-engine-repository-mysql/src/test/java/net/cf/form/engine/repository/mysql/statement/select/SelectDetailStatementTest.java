package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.testcases.statement.select.SelectDetailExpandTest;
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
public class SelectDetailStatementTest implements SelectDetailExpandTest {

    // 查询子表展开部分字段字段
    private static final String SELECT_TRAVEL_AND_SCHEDULES_EXPAND_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT, OQL.TRV_SCHE_TB.SCHE_ID, OQL.TRV_SCHE_TB.APPLY_ID, OQL.TRV_SCHE_TB.START_DATE, OQL.TRV_SCHE_TB.END_DATE, OQL.TRV_SCHE_TB.DEST, OQL.TRV_SCHE_TB.TODO, OQL.TRV_SCHE_TB.STATUS from OQL.TRV_TB left join OQL.TRV_SCHE_TB on OQL.TRV_TB.APPLY_ID = OQL.TRV_SCHE_TB.APPLY_ID";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testSelectTravelAndScheduleExpand1() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_AND_SCHEDULES_EXPAND1_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_AND_SCHEDULES_EXPAND_SQL, sql));
    }

    @Test
    @Override
    public void testSelectTravelAndScheduleExpand2() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_AND_SCHEDULES_EXPAND2_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_AND_SCHEDULES_EXPAND_SQL, sql));
    }

}
