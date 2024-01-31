package net.cf.form.engine.repository.mysql.statement.update;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.UpdateDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import net.cf.form.engine.repository.testcases.statement.update.UpdateMainAndDetailTest;
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
 * 测试主对象（出差）+子对象（行程）的 Update 语句的生成
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class UpdateMainAndDetailStatementTest implements UpdateMainAndDetailTest {

    /**
     * 更新出差主表SQL
     */
    private static final String UPDATE_TRAVEL_SQL = "update OQL.TRV_TB set USER_ID = 'zhangsan', START_DATE = '2023-06-07', END_DATE = '2023-06-10', REASON = '出差培训', ATTACH = '{\"name\": \"这是一个文件名\", \"id\": 123}' where APPLY_ID = 1";

    /**
     * 更新出差行程表SQL（示意SQL，实际不可执行）
     */
    private static final String UPDATE_TRAVEL_SCHEDULES_SQL = "update OQL.TRV_SCHE_TB set (SCHE_ID, START_DATE, END_DATE, DEST, TODO) = '[(null, '2023-06-07', '2023-06-08', '北京', '项目会议'), (1, '2023-06-07', '2023-06-08', '南京', '学术会议')]'";

    /**
     * 更新（带变量的）出差主表SQL
     */
    private static final String UPDATE_TRAVEL_VAR_SQL = "update OQL.TRV_TB set USER_ID = :userId, START_DATE = :startDate, END_DATE = :endDate, REASON = :reason, ATTACH = :attach where APPLY_ID = :applyId";

    /**
     * 更新（带变量的）出差行程SQL（示意SQL，实际不可执行）
     */
    private static final String UPDATE_TRAVEL_SCHEDULES_VAR_SQL = "update OQL.TRV_SCHE_TB set (SCHE_ID, START_DATE, END_DATE, DEST, TODO) = '[(:scheduleId, :startDate, :endDate, :destination, :todo)]'";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testUpdateTravelAndSchedules() {
        UpdateSqlInfo sqlInfo = SqlTestUtils.parseUpdateOql(UPDATE_TRAVEL_AND_SCHEDULES_OQL, resolver);
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_SQL, sql));

        Map<String, UpdateDetailSqlInfo> detailUpdateSqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailUpdateSqlInfoMap.size() == 1);
        UpdateDetailSqlInfo detailSqlInfo = detailUpdateSqlInfoMap.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        Assert.assertTrue("TravelSchedule".equalsIgnoreCase(detailSqlInfo.getObject().getName()));
        String detailSql = new UpdateSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_SCHEDULES_SQL, detailSql));
    }

    @Test
    @Override
    public void testUpdateTravelAndSchedulesVar() {
        UpdateSqlInfo sqlInfo = SqlTestUtils.parseUpdateOql(UPDATE_TRAVEL_AND_SCHEDULES_VAR_OQL, resolver, true);
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_VAR_SQL, sql));

        Map<String, UpdateDetailSqlInfo> detailUpdateSqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(detailUpdateSqlInfoMap.size() == 1);
        UpdateDetailSqlInfo detailSqlInfo = detailUpdateSqlInfoMap.get("schedules");
        Assert.assertTrue(detailSqlInfo != null);
        Assert.assertTrue("TravelSchedule".equalsIgnoreCase(detailSqlInfo.getObject().getName()));
        String detailSql = new UpdateSqlBuilder(detailSqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(UPDATE_TRAVEL_SCHEDULES_VAR_SQL, detailSql));
    }
}
