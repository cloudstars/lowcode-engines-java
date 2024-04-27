package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.Map;

public abstract class AbstractSelectStaffColumnExprWhereRepoTest
        extends AbstractOqlRepoTest
        implements SelectStaffColumnExprWhereTest {

    protected AbstractSelectStaffColumnExprWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testSelectListWithCreateEqualsOwner() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_LIST_WITH_CREATOR_EQUALS_OWNER);
        OqlSelectStatement stmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engineNew.queryOne(stmt);
        assert (resultMap != null);
    }

}
