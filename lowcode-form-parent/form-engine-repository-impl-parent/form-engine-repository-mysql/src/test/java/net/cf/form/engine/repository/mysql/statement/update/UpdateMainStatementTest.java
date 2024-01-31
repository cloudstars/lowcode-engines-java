package net.cf.form.engine.repository.mysql.statement.update;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import net.cf.form.engine.repository.testcases.statement.update.UpdateMainTest;
import net.cf.form.engine.repository.mysql.statement.SqlTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * 测试主对象（出差）的 Update 语句的生成
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class UpdateMainStatementTest implements UpdateMainTest {

    /**
     * 更新（常量的）主差对象的SQL
     */
    private final String UPDATE_TRAVEL_SQL = "update OQL.TRV_TB set USER_ID = 'zhangsan', START_DATE = '2023-06-08', END_DATE = '2023-06-11', REASON = '出差培训（变更）', ATTACH = '{\"name\": \"这是一个文件名\", \"id\": 124}' where APPLY_ID = 1";

    /**
     * 更新（带变量的）主差对象的SQL
     */
    private final String UPDATE_TRAVEL_VAR_SQL = "update OQL.TRV_TB set USER_ID = :userId, START_DATE = :startDate, END_DATE = :endDate, REASON = :reason, ATTACH = :attach where APPLY_ID = :applyId";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testUpdateTravel() {
        UpdateSqlInfo sqlInfo = SqlTestUtils.parseUpdateOql(UPDATE_TRAVEL_OQL, resolver);
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_SQL, sql));
    }

    @Test
    @Override
    public void testUpdateTravelVar() {
        UpdateSqlInfo sqlInfo = SqlTestUtils.parseUpdateOql(UPDATE_TRAVEL_VAR_OQL, resolver, true);
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_VAR_SQL, sql));
    }
}
