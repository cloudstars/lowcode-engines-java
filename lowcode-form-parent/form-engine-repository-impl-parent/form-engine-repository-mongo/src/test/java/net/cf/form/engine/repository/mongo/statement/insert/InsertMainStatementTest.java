package net.cf.form.engine.repository.mongo.statement.insert;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.testcases.statement.insert.InsertMainTest;
import net.cf.form.engine.repository.mongo.TestApplication;
import net.cf.form.engine.repository.mongo.statement.SqlUtils;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.parser.OqlInsertParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试 Insert 语句的生成
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class InsertMainStatementTest implements InsertMainTest {

    // 简单的插入语句产生的SQL语句
    private static final String INSERT_TRAVEL_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH, ENTP_KEY, CREATED_BY, CREATED_AT) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}', 'CMB', 'zhangsan', '2023-06-07 11:11:11.123')";
    private static final String INSERT_TRAVEL_MONGO_COMMAND = "db.getCollection('OQL.TRV_TB').insertMany([{\"USER_ID\": \"zhangsan\", \"START_DATE\": \"2023-06-07\", \"END_DATE\": \"2023-06-10\", \"REASON\": \"出差培训\", \"ATTACH\": {\"name\": \"这是一个文件名\", \"id\": 123}, \"ENTP_KEY\": \"CMB\", \"CREATED_BY\": \"zhangsan\", \"CREATED_AT\": \"2023-06-07 11:11:11.123\"}])";
    // 带变量的插入语句产生的SQL语句
    private static final String INSERT_TRAVEL_VAR_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH, ENTP_KEY, CREATED_BY, CREATED_AT) values (:userId, :startDate, :endDate, :reason, :attach, :enterpriseKey, :createdBy, :createdAt)";

    // 批量的插入语句产生的SQL语句
    private static final String INSERT_MULTI_TRAVELS_SQL = "insert into OQL.TRV_TB(USER_ID, START_DATE, END_DATE, REASON, ATTACH, ENTP_KEY, CREATED_BY, CREATED_AT) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是一个文件名\", \"id\": 123}', 'CMB', 'zhangsan', '2023-06-07 11:11:11.123'), ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', '{\"name\": \"这是另一个文件名\", \"id\": 124}', 'CMB', 'zhangsan', '2023-06-07 11:11:11.123')";

    @Resource
    private DataObjectResolver resolver;


    @Test
    @Override
    public void testInsertTravel() {
        OqlInsertParser insertParser = new OqlInsertParser(INSERT_TRAVEL_OQL);
        OqlInsertStatement stmt = new OqlInsertStatement(insertParser.insertInto());
        MongoInsertSqlAstVisitor visitor = new MongoInsertSqlAstVisitor(resolver);
        stmt.accept(visitor);
        MongoInsertCommand insertSql = visitor.getSql();
        System.out.println(insertSql.getSqlExpr());
        Assert.assertTrue(SqlUtils.equals(insertSql.getSqlExpr(), INSERT_TRAVEL_MONGO_COMMAND));
    }

    @Test
    @Override
    public void testInsertTravelVar() {

    }

    @Test
    @Override
    public void testInsertMultiTravels() {
    }


    @Override
    public void testInsertMultiTravelsVar() {

    }

}
