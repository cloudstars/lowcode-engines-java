package net.cf.form.engine.repository.mongo.statement.update;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.update.UpdateMainTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateMainDataTest implements UpdateMainTest {

    /**
     * 更新第一条记录的SQL
     */
    private final String UPDATE_TRAVEL_SQL = "update OQL.TRV_TB set REASON = '原因：行程变更' where APPLY_ID = 1";

    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testUpdateTravel() {
        OqlUpdateStatement stmt = OqlUtils.parseSingleUpdateStatement(UPDATE_TRAVEL_SQL);
        MongoUpdateSqlAstVisitor visitor = new MongoUpdateSqlAstVisitor(resolver);
    }

    @Override
    public void testUpdateTravelVar() {

    }
}
