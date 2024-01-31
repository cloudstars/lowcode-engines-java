package net.cf.form.engine.repository.mysql.statement.insert;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.testcases.statement.insert.InsertMainTest;
import net.cf.form.engine.repository.mysql.TestApplication;
import net.cf.form.engine.repository.mysql.statement.SqlTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试主对象（出差）的 Insert 语句的生成
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("h2")
public class InsertMainStatementTest implements InsertMainTest {

    // 简单的插入语句产生的SQL语句
    private static final String INSERT_TRAVEL_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}')";

    // 带变量的插入语句产生的SQL语句
    private static final String INSERT_TRAVEL_VAR_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values (:userId, :startDate, :endDate, :reason, :attach)";

    // 多条插入语句产生的SQL语句
    private static final String INSERT_MULTI_TRAVELS_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}'), ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是另一个文件名\", \"id\": 124}')";

    // 带变量的多条插入语句产生的SQL语句
    private static final String INSERT_MULTI_TRAVELS_VAR_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values (:userId0, :startDate0, :endDate0, :reason0, :attach0), (:userId1, :startDate1, :endDate1, :reason1, :attach1)";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testInsertTravel() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_TRAVEL_OQL, resolver);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_TRAVEL_SQL, sql));
    }

    @Test
    @Override
    public void testInsertTravelVar() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_TRAVEL_VAR_OQL, resolver, true);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_TRAVEL_VAR_SQL, sql));
    }

    @Test
    @Override
    public void testInsertMultiTravels() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_MULTI_TRAVELS_OQL, resolver);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVELS_SQL, sql));
    }

    @Test
    @Override
    public void testInsertMultiTravelsVar() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_MULTI_TRAVELS_VAR_OQL, resolver, true);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVELS_VAR_SQL, sql));
    }

}
