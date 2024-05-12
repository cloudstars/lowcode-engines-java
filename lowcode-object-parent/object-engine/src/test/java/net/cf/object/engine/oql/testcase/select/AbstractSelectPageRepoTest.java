package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.data.PageRequest;
import net.cf.object.engine.data.PageResult;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSelectPageRepoTest
        extends AbstractOqlRepoTest
        implements SelectPageTest {

    protected AbstractSelectPageRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testSelectStaffPageList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_PAGE_LIST);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        PageResult<Map<String, Object>> resultPage = this.engine.queryPage(oqlStmt, new HashMap<>(), new PageRequest(0, 1));
        assert (resultPage.getTotal() == 2);
        assert (resultPage.getList().size() == 1);
    }

}
