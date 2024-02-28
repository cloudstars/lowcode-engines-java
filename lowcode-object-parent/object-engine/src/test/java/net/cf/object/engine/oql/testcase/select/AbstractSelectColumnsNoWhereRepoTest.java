package net.cf.object.engine.oql.testcase.select;

import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.XObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.SqlStatementUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractSelectColumnsNoWhereRepoTest extends AbstractOqlRepoTest implements ISelectColumnsNoWhereTest {

    protected AbstractSelectColumnsNoWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelList() {
        String oql = this.oqlMap.get(OQL_SELECT_TRAVEL_LIST);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oql);
        XObjectTestUtils.resolveObject(oqlStmt);
        SqlSelectStatement sqlStmt = SqlStatementUtils.fromOqlSelect(oqlStmt);
        List<Map<String, Object>> dataList = this.repository.selectList(sqlStmt);
        assert (dataList.size() == 2);
    }
}
