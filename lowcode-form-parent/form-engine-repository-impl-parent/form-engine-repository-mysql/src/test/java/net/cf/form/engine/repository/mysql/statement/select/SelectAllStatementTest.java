package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.SelectDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.testcases.statement.select.SelectAllTest;
import net.cf.form.engine.repository.mysql.statement.SqlTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class SelectAllStatementTest implements SelectAllTest {

    /**
     * 查询全部出差数据SQL
     */
    private static final String SELECT_TRAVEL_ALL_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_TB";

    /**
     * 查询全部行程数据SQL
     */
    private static final String SELECT_SCHEDULE_ALL_SQL = "select SCHE_ID, APPLY_ID, START_DATE, END_DATE, DEST, TODO, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_SCHE_TB";

    /**
     * 查询全部报销数据SQL
     */
    private static final String SELECT_CLAIM_ALL_SQL = "select CLAIM_ID, STA_ID, SCHE_IDS, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from  OQL.TRV_CLM_TB";

    /**
     * 查询全部出差 + 行程子表数据SQL
     */
    private static final String SELECT_TRAVEL_AND_SCHEDULE_ALL_SQL = "select OQL.TRV_TB.APPLY_ID, OQL.TRV_TB.USER_ID, OQL.TRV_TB.START_DATE, OQL.TRV_TB.END_DATE, OQL.TRV_TB.REASON, OQL.TRV_TB.ATTACH, OQL.TRV_TB.STATUS, OQL.TRV_TB.ENTP_KEY, OQL.TRV_TB.CREATED_BY, OQL.TRV_TB.CREATED_AT, OQL.TRV_TB.MODIFIED_BY, OQL.TRV_TB.MODIFIED_AT, OQL.TRV_SCHE_TB.SCHE_ID, OQL.TRV_SCHE_TB.APPLY_ID, OQL.TRV_SCHE_TB.START_DATE, OQL.TRV_SCHE_TB.END_DATE, OQL.TRV_SCHE_TB.DEST, OQL.TRV_SCHE_TB.TODO, OQL.TRV_SCHE_TB.STATUS, OQL.TRV_SCHE_TB.ENTP_KEY, OQL.TRV_SCHE_TB.CREATED_BY, OQL.TRV_SCHE_TB.CREATED_AT, OQL.TRV_SCHE_TB.MODIFIED_BY, OQL.TRV_SCHE_TB.MODIFIED_AT from OQL.TRV_TB left join OQL.TRV_SCHE_TB on OQL.TRV_TB.APPLY_ID = OQL.TRV_SCHE_TB.APPLY_ID";


    /**
     * 查询全部出差行程 + 出差主表数据SQL
     */
    private static final String SELECT_SCHEDULE_AND_TRAVEL_ALL_SQL = "select OQL.TRV_SCHE_TB.SCHE_ID, OQL.TRV_SCHE_TB.APPLY_ID, OQL.TRV_SCHE_TB.START_DATE, OQL.TRV_SCHE_TB.END_DATE, OQL.TRV_SCHE_TB.DEST, OQL.TRV_SCHE_TB.TODO, OQL.TRV_SCHE_TB.STATUS, OQL.TRV_SCHE_TB.ENTP_KEY, OQL.TRV_SCHE_TB.CREATED_BY, OQL.TRV_SCHE_TB.CREATED_AT, OQL.TRV_SCHE_TB.MODIFIED_BY, OQL.TRV_SCHE_TB.MODIFIED_AT, OQL.TRV_TB.APPLY_ID, OQL.TRV_TB.USER_ID, OQL.TRV_TB.START_DATE, OQL.TRV_TB.END_DATE, OQL.TRV_TB.REASON, OQL.TRV_TB.ATTACH, OQL.TRV_TB.STATUS, OQL.TRV_TB.ENTP_KEY, OQL.TRV_TB.CREATED_BY, OQL.TRV_TB.CREATED_AT, OQL.TRV_TB.MODIFIED_BY, OQL.TRV_TB.MODIFIED_AT from OQL.TRV_SCHE_TB left join OQL.TRV_TB on OQL.TRV_SCHE_TB.APPLY_ID = OQL.TRV_TB.APPLY_ID";

    /**
     * 查询全部出差报销 + 相关表（多选）数据SQL
     */
    private static final String SELECT_CLAIM_AND_SCHEDULE_ALL_SQL = "select CLAIM_ID, STA_ID, SCHE_IDS, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_CLM_TB";

    /**
     * 查询全部出差报销 + 相关表（多选）的情况下，相关表要生成一个独立一个数据SQL
     */
    private static final String SELECT_CLAIM_AND_SCHEDULE_ALL_LOOKUP_SQL = "select SCHE_ID, APPLY_ID, START_DATE, END_DATE, DEST, TODO, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_SCHE_TB";

    /**
     * 查询全部出差报销 + 员工（单选）相关表数据SQL
     */
    private static final String SELECT_CLAIM_AND_STAFF_ALL_SQL = "select OQL.TRV_CLM_TB.CLAIM_ID, OQL.TRV_CLM_TB.STA_ID, OQL.TRV_CLM_TB.SCHE_IDS, OQL.TRV_CLM_TB.STATUS, OQL.TRV_CLM_TB.ENTP_KEY, OQL.TRV_CLM_TB.CREATED_BY, OQL.TRV_CLM_TB.CREATED_AT, OQL.TRV_CLM_TB.MODIFIED_BY, OQL.TRV_CLM_TB.MODIFIED_AT, OQL.STA_TB.STA_ID, OQL.STA_TB.STA_NAME, OQL.STA_TB.STATUS, OQL.STA_TB.ENTP_KEY, OQL.STA_TB.CREATED_BY, OQL.STA_TB.CREATED_AT, OQL.STA_TB.MODIFIED_BY, OQL.STA_TB.MODIFIED_AT from OQL.TRV_CLM_TB left join OQL.STA_TB on OQL.TRV_CLM_TB.STA_ID = OQL.STA_TB.STA_ID";

    @Resource
    private DataObjectResolver resolver;


    @Test
    @Override
    public void testSelectTravelAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_ALL_SQL, sql));
    }

    @Test
    @Override
    public void testSelectScheduleAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_SCHEDULE_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_SCHEDULE_ALL_SQL, sql));
    }

    @Test
    @Override
    public void testSelectClaimAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_CLAIM_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_CLAIM_ALL_SQL, sql));
    }

    @Test
    @Override
    public void testSelectTravelAndScheduleAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_TRAVEL_AND_SCHEDULE_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_TRAVEL_AND_SCHEDULE_ALL_SQL, sql));
    }

    @Test
    @Override
    public void testSelectScheduleAndTravelAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_SCHEDULE_AND_TRAVEL_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_SCHEDULE_AND_TRAVEL_ALL_SQL, sql));
    }

    @Test
    @Override
    public void testSelectClaimAndScheduleAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_CLAIM_AND_SCHEDULE_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_CLAIM_AND_SCHEDULE_ALL_SQL, sql));
        Map<String, SelectDetailSqlInfo> individualLookupSqlInfos = sqlInfo.getDetailSqlInfoMap();
        Assert.assertTrue(individualLookupSqlInfos.size() == 1);
        for (Map.Entry<String, SelectDetailSqlInfo> entry : individualLookupSqlInfos.entrySet()) {
            String lookupSql = new SelectSqlBuilder(entry.getValue()).buildSql();
            Assert.assertTrue(SqlTestUtils.equals(SELECT_CLAIM_AND_SCHEDULE_ALL_LOOKUP_SQL, lookupSql));
            break;
        }
    }

    @Test
    @Override
    public void testSelectClaimAndStaffAll() {
        SelectSqlInfo sqlInfo = SqlTestUtils.parseSelectOql(SELECT_CLAIM_AND_STAFF_ALL_OQL, resolver);
        String sql = new SelectSqlBuilder(sqlInfo).buildSql();
        Assert.assertTrue(SqlTestUtils.equals(SELECT_CLAIM_AND_STAFF_ALL_SQL, sql));
    }
}
