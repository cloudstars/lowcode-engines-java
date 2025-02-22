
package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

public abstract class AbstractUpdateDetailOnlyRepoTest
        extends AbstractOqlRepoTest
        implements UpdateDetailOnlyTest {

    protected AbstractUpdateDetailOnlyRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testUpdateDetailOnlyWithDotVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_DETAIL_ONLY_WITH_DOT_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt, oqlInfo.paramMap);
        assert (effectedRows == 1);
    }
}
