package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.mongo.statement.SqlUtils;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.testcases.statement.select.SelectAllTest;
import net.cf.form.engine.repository.mongo.statement.ObjectContextHolder;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Deprecated
public class SelectAllStatementTestImpl implements SelectAllTest {

    /**
     * 查询全部出差数据SQL
     */
    private static final String SELECT_TRAVEL_ALL_MONGO_SQL = "{\"aggregate\": \"OQL.TRV_TB\", \"pipeline\": [{\"$project\": {\"applyId\": 1, \"userId\": 1, \"startDate\": 1, \"endDate\": 1, \"reason\": 1, \"attach\": 1, \"status\": 1, \"enterpriseKey\": 1, \"createdBy\": 1, \"createdAt\": 1, \"modifiedBy\": 1, \"modifiedAt\": 1}}]}";
    /**
     * 查询全部行程数据SQL
     */
    private static final String SELECT_SCHEDULE_ALL_SQL = "select SCHE_ID, APPLY_ID, START_DATE, END_DATE, DEST, TODO, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_SCHE_TB";
    private static final String SELECT_SCHEDULE_ALL_MONGO_SQL = "{\"aggregate\": \"OQL.TRV_SCHE_TB\", \"pipeline\": [{\"$project\": {\"scheduleId\": 1, \"travel\": 1, \"startDate\": 1, \"endDate\": 1, \"destination\": 1, \"todo\": 1, \"status\": 1, \"enterpriseKey\": 1, \"createdBy\": 1, \"createdAt\": 1, \"modifiedBy\": 1, \"modifiedAt\": 1}}]}";
    /**
     * 查询全部报销数据SQL
     */
    private static final String SELECT_CLAIM_ALL_SQL = "select CLAIM_ID, STA_ID, SCHE_IDS, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from  OQL.TRV_CLM_TB";

    /**
     * 查询全部出差 + 行程子表数据SQL
     */
    private static final String SELECT_TRAVEL_AND_SCHEDULE_ALL_SQL = "select APPLY_ID, USER_ID, START_DATE, END_DATE, REASON, ATTACH, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT, OQL.TRV_SCHE_TB.SCHE_ID, OQL.TRV_SCHE_TB.APPLY_ID, OQL.TRV_SCHE_TB.START_DATE, OQL.TRV_SCHE_TB.END_DATE, OQL.TRV_SCHE_TB.DEST, OQL.TRV_SCHE_TB.TODO, OQL.TRV_SCHE_TB.STATUS, OQL.TRV_SCHE_TB.ENTP_KEY, OQL.TRV_SCHE_TB.CREATED_BY, OQL.TRV_SCHE_TB.CREATED_AT, OQL.TRV_SCHE_TB.MODIFIED_BY, OQL.TRV_SCHE_TB.MODIFIED_AT from OQL.TRV_TB left join OQL.TRV_SCHE_TB on OQL.TRV_TB.APPLY_ID = OQL.TRV_SCHE_TB.APPLY_ID";
    private static final String SELECT_TRAVEL_AND_SECHDULE_ALL_MONGO_SQL = "{\"aggregate\": \"OQL.TRV_TB\", \"pipeline\": [{\"$lookup\": {\"from\": \"OQL.TRV_SCHE_TB\", \"as\": \"TravelSchedule\", \"let\": {\"MASTER\": \"$APPLY_ID\"}, \"pipeline\": [{\"$match\": {\"$expr\": {\"$eq\": [\"$$MASTER\", \"$APPLY_ID\"]}}}]}}, {\"$project\": {\"applyId\": 1, \"userId\": 1, \"startDate\": 1, \"endDate\": 1, \"reason\": 1, \"attach\": 1, \"status\": 1, \"enterpriseKey\": 1, \"createdBy\": 1, \"createdAt\": 1, \"modifiedBy\": 1, \"modifiedAt\": 1, \"TravelSchedule.scheduleId\": 1, \"TravelSchedule.travel\": 1, \"TravelSchedule.startDate\": 1, \"TravelSchedule.endDate\": 1, \"TravelSchedule.destination\": 1, \"TravelSchedule.todo\": 1, \"TravelSchedule.status\": 1, \"TravelSchedule.enterpriseKey\": 1, \"TravelSchedule.createdBy\": 1, \"TravelSchedule.createdAt\": 1, \"TravelSchedule.modifiedBy\": 1, \"TravelSchedule.modifiedAt\": 1}}]}";
    /**
     * 查询全部出差行程 + 出差主表数据SQL
     */
    private static final String SELECT_SCHEDULE_AND_TRAVEL_ALL_SQL = "select SCHE_ID, APPLY_ID, START_DATE, END_DATE, DEST, TODO, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT, OQL.TRV_TB.APPLY_ID, OQL.TRV_TB.USER_ID, OQL.TRV_TB.START_DATE, OQL.TRV_TB.END_DATE, OQL.TRV_TB.REASON, OQL.TRV_TB.ATTACH, OQL.TRV_TB.STATUS, OQL.TRV_TB.ENTP_KEY, OQL.TRV_TB.CREATED_BY, OQL.TRV_TB.CREATED_AT, OQL.TRV_TB.MODIFIED_BY, OQL.TRV_TB.MODIFIED_AT from OQL.TRV_SCHE_TB left join OQL.TRV_TB on OQL.TRV_SCHE_TB.APPLY_ID = OQL.TRV_TB.APPLY_ID";

    /**
     * 查询全部出差报销 + 相关表（多选）数据SQL
     */
    private static final String SELECT_CLAIM_AND_SCHEDULE_ALL_SQL = "select CLAIM_ID, STA_ID, SCHE_IDS, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_CLM_TB";

    /**
     * 查询全部出差报销 + 相关表（多选）的情况下，相关表要生成一个独立一个数据SQL
     */
    private static final String SELECT_CLAIM_AND_SCHEDULE_ALL_LOOKUP_SQL = "select SCHE_ID, APPLY_ID, START_DATE, END_DATE, DEST, TODO, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT from OQL.TRV_SCHE_TB where SCHE_ID in (:schedules)";

    /**
     * 查询全部出差报销 + 员工（单选）相关表数据SQL
     */
    private static final String SELECT_CLAIM_AND_STAFF_ALL_SQL = "select CLAIM_ID, STA_ID, SCHE_IDS, STATUS, ENTP_KEY, CREATED_BY, CREATED_AT, MODIFIED_BY, MODIFIED_AT, OQL.STA_TB.STA_ID, OQL.STA_TB.STA_NAME, OQL.STA_TB.STATUS, OQL.STA_TB.ENTP_KEY, OQL.STA_TB.CREATED_BY, OQL.STA_TB.CREATED_AT, OQL.STA_TB.MODIFIED_BY, OQL.STA_TB.MODIFIED_AT from OQL.TRV_CLM_TB left join OQL.STA_TB on OQL.TRV_CLM_TB.STA_ID = OQL.STA_TB.STA_ID";

    @Resource
    private DataObjectResolver resolver;



    @Test
    public void testSelectTravelAl2l() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement("select * from Travel where 1 = 1 and applyId = 'a'");
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        ObjectContextHolder.remove();
    }


    @Test
    @Override
    public void testSelectTravelAll() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_TRAVEL_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        Assert.assertTrue(SqlUtils.equals(SELECT_TRAVEL_ALL_MONGO_SQL, mongoSelectCommand.getMongoOql()));
        ObjectContextHolder.remove();
    }

    @Test
    @Override
    public void testSelectScheduleAll() {

        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_SCHEDULE_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        Assert.assertTrue(SqlUtils.equals(SELECT_SCHEDULE_ALL_MONGO_SQL, mongoSelectCommand.getMongoOql()));
        ObjectContextHolder.remove();

    }

    @Test
    @Override
    public void testSelectClaimAll() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_CLAIM_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        ObjectContextHolder.remove();
    }

    @Test
    @Override
    public void testSelectTravelAndScheduleAll() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_TRAVEL_AND_SCHEDULE_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        Assert.assertTrue(SqlUtils.equals(SELECT_TRAVEL_AND_SECHDULE_ALL_MONGO_SQL, mongoSelectCommand.getMongoOql()));
        ObjectContextHolder.remove();
    }

    @Test
    @Override
    public void testSelectScheduleAndTravelAll() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_SCHEDULE_AND_TRAVEL_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        ObjectContextHolder.remove();
    }

    @Test
    @Override
    public void testSelectClaimAndScheduleAll() {
        ObjectContextHolder.init();
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_CLAIM_AND_SCHEDULE_ALL_OQL);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
//        Assert.assertTrue(OqlUtils.equals(SELECT_TRAVEL_AND_SECHDULE_ALL_MONGO_SQL, mongoSelectCommand.getMongoOql()));
        ObjectContextHolder.remove();
    }

    @Test
    @Override
    public void testSelectClaimAndStaffAll() {
    }
}
