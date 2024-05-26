package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectCaseWhenRepoTest
        extends AbstractOqlRepoTest
        implements SelectCaseWhenTest {

    protected AbstractSelectCaseWhenRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }


    @Override
    public void testSelectCaseWhen() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_CASE_WHEN);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultList = this.engine.queryList(oqlStmt, new HashMap<>());
        assert (resultList.size() == 2);
        assert (DataCompareTestUtils.equalsList(resultList, oqlInfo.resultMaps));
    }

}
