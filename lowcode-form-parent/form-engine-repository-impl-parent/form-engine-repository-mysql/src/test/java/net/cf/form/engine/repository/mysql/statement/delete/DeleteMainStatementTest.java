package net.cf.form.engine.repository.mysql.statement.delete;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import net.cf.form.engine.repository.testcases.statement.delete.DeleteMainTest;
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
public class DeleteMainStatementTest implements DeleteMainTest {

    /**
     * 更新第一条记录的SQL
     */
    private final String DELETE_TRAVEL_SQL = "delete from OQL.TRV_TB where APPLY_ID = 1";

    /**
     * 更新(带变量的)第一条记录的SQL
     */
    private final String DELETE_TRAVEL_VAR_SQL = "delete from OQL.TRV_TB where APPLY_ID = :applyId";

    /**
     * 按in过滤更新记录的SQL
     */
    private final String DELETE_TRAVEL_IN_SQL = "delete from OQL.TRV_TB where APPLY_ID in (1, 2)";

    /**
     * 按in过滤更新(带变量的)记录的SQL
     */
    private final String DELETE_TRAVEL_IN_VAR_SQL = "delete from OQL.TRV_TB where APPLY_ID in (:applyId0, :applyId1)";


    @Resource
    private DataObjectResolver resolver;

    @Test
    @Override
    public void testDeleteTravel() {
        DeleteSqlInfo sqlInfo = SqlTestUtils.parseDeleteOql(DELETE_TRAVEL_OQL, resolver);
        String sql = new DeleteSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(DELETE_TRAVEL_SQL, sql));
    }

    @Test
    @Override
    public void testDeleteTravelVar() {
        DeleteSqlInfo sqlInfo = SqlTestUtils.parseDeleteOql(DELETE_TRAVEL_VAR_OQL, resolver, true);
        String sql = new DeleteSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(DELETE_TRAVEL_VAR_SQL, sql));
    }

    @Test
    @Override
    public void testDeleteTravelIn() {
        DeleteSqlInfo sqlInfo = SqlTestUtils.parseDeleteOql(DELETE_TRAVEL_IN_OQL, resolver);
        String sql = new DeleteSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(DELETE_TRAVEL_IN_SQL, sql));
    }

    @Test
    @Override
    public void testDeleteTravelInVar() {
        DeleteSqlInfo sqlInfo = SqlTestUtils.parseDeleteOql(DELETE_TRAVEL_IN_VAR_OQL, resolver);
        String sql = new DeleteSqlBuilder(sqlInfo).getSql();
        Assert.assertTrue(SqlTestUtils.equals(DELETE_TRAVEL_IN_VAR_SQL, sql));
    }
}
