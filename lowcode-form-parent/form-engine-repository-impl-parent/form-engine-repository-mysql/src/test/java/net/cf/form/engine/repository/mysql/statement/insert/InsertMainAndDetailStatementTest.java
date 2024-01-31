package net.cf.form.engine.repository.mysql.statement.insert;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.InsertDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.testcases.statement.insert.InsertMainAndDetailTest;
import net.cf.form.engine.repository.mysql.TestApplication;
import net.cf.form.engine.repository.mysql.statement.SqlTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 测试主对象（出差）+子对象（行程）的 Insert 语句的生成
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("h2")
public class InsertMainAndDetailStatementTest implements InsertMainAndDetailTest {

    // 插入出差主对象产生的SQL语句
    private static final String INSERT_TRAVEL_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values ('wangwu', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}')";

    // 插入出差行程子对象产生的SQL语句
    private static final String INSERT_TRAVEL_SCHEDULES_SQL = "insert into OQL.TRV_SCHE_TB(START_DATE, END_DATE, DEST, TODO) values ('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨')";

    // 插入（带变量的）出差主对象产生的SQL语句
    private static final String INSERT_TRAVEL_VAR_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values (:userId, :startDate, :endDate, :reason, :attach)";

    // 插入（带变量的）出差行程子对象产生的SQL语句
    private static final String INSERT_TRAVEL_SCHEDULES_VAR_SQL = "insert into OQL.TRV_SCHE_TB(START_DATE, END_DATE, DEST, TODO) values (:startDate, :endDate, :destination, :todo)";

    // 插入（多条）出差主对象产生的SQL语句
    private static final String INSERT_MULTI_TRAVELS_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values ('wangwu', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}'), ('wangwu', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是另一个文件名\", \"id\": 124}')";

    // 插入（多条）出差行程子对象产生的SQL语句
    private static final String INSERT_MULTI_TRAVEL_SCHEDULES_SQL = "insert into OQL.TRV_SCHE_TB(START_DATE, END_DATE, DEST, TODO) values ('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨'), ('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨'), ('2023-06-09', '2023-06-10', '天津', '公费旅游')";

    // 插入（带变量的）（多条）出差主对象产生的SQL语句
    private static final String INSERT_MULTI_TRAVELS_VAR_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH) values (:userId0, :startDate0, :endDate0, :reason0, :attach0), (:userId1, :startDate1, :endDate1, :reason1, :attach1)";

    // 插入（带变量的）（多条）出差行程子对象产生的SQL语句
    private static final String INSERT_MULTI_TRAVEL_SCHEDULES_VAR_SQL = "insert into OQL.TRV_SCHE_TB(START_DATE, END_DATE, DEST, TODO) values (:startDate, :endDate, :destination, :todo), (:startDate, :endDate, :destination, :todo)";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testInsertTravelsAndSchedules() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_TRAVELS_AND_SCHEDULES_OQL, resolver);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_TRAVEL_SQL, sql));

        Map<String, InsertDetailSqlInfo> detailSqlInfos = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailSqlInfos.size() == 1);
        InsertDetailSqlInfo detailSqlInfo = detailSqlInfos.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        String detailSql = new InsertSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_TRAVEL_SCHEDULES_SQL, detailSql));
    }

    @Test
    @Override
    public void testInsertTravelsAndSchedulesVar() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_TRAVELS_AND_SCHEDULES_VAR_OQL, resolver, true);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(sql, INSERT_TRAVEL_VAR_SQL));

        Map<String, InsertDetailSqlInfo> detailSqlInfos = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailSqlInfos.size() == 1);
        InsertDetailSqlInfo detailSqlInfo = detailSqlInfos.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        String detailSql = new InsertSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_TRAVEL_SCHEDULES_VAR_SQL, detailSql));
    }

    @Test
    @Override
    public void testInsertMultiTravelsAndSchedules() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_MULTI_TRAVELS_AND_SCHEDULES_OQL, resolver);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVELS_SQL, sql));

        Map<String, InsertDetailSqlInfo> detailSqlInfos = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailSqlInfos.size() == 1);
        InsertDetailSqlInfo detailSqlInfo = detailSqlInfos.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        String detailSql = new InsertSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVEL_SCHEDULES_SQL, detailSql));
    }

    @Test
    @Override
    public void testInsertMultiTravelsAndSchedulesVar() {
        InsertSqlInfo sqlInfo = SqlTestUtils.parseInsertOql(INSERT_MULTI_TRAVELS_AND_SCHEDULES_VAR_OQL, resolver, true);
        String sql = new InsertSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVELS_VAR_SQL, sql));

        Map<String, InsertDetailSqlInfo> detailSqlInfos = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailSqlInfos.size() == 1);
        InsertDetailSqlInfo detailSqlInfo = detailSqlInfos.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        String detailSql = new InsertSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(INSERT_MULTI_TRAVEL_SCHEDULES_VAR_SQL, detailSql));
    }

}
