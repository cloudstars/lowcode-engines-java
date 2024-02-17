package net.cf.form.repository.mysql.data.insert;

import net.cf.form.repository.mysql.MySQLFormRepositoryImpl;
import net.cf.form.repository.mysql.MysqlRepositoryTestApplication;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import net.cf.form.repository.testcases.statement.insert.AbstractSimpleInsertSqlsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MysqlRepositoryTestApplication.class)
@ActiveProfiles("test")
public class SimpleInsertSqlsTest extends AbstractSimpleInsertSqlsTest {

    @Resource
    private MySQLFormRepositoryImpl repository;

    @Test
    @Override
    public void testInsertWithoutParams() {
        super.testInsertWithoutParams();
    }

    @Override
    protected void testInsertWithoutParams(String sql) {
        SqlInsertStatement stmt = SqlStatementUtils.parseSingleInsertStatement(sql);
        this.repository.insert(stmt);
    }

    @Test
    @Override
    public void testInsertWithParams() {
        super.testInsertWithParams();
    }

    @Override
    protected void testInsertWithParams(String sql) {
        SqlInsertStatement stmt = SqlStatementUtils.parseSingleInsertStatement(sql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("xx", "");
        // TODO
        this.repository.insert(stmt, paramMap);
    }
}
