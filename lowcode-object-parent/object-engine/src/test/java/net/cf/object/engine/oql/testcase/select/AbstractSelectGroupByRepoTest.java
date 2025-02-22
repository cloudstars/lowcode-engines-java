package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectGroupByRepoTest
        extends AbstractOqlRepoTest
        implements SelectGroupByTest {

    protected AbstractSelectGroupByRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Test
    @Override
    public void testSelectGroupByStaffCode() {
    OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_GROUP_BY);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultList = this.engine.queryList(oqlStmt, new HashMap<>());
        assert (resultList.size() == 2);
        assert (DataCompareTestUtils.equalsList(resultList, oqlInfo.resultMaps));
    }

}
