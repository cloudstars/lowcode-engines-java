package net.cf.object.engine.oql.testcase.select;

import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectMultiValuesRepoTest
        extends AbstractOqlRepoTest
        implements SelectMultiValuesTest {

    protected AbstractSelectMultiValuesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testSelectStaffHobbiesMultiValueList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_MULTI_VALUE_LIST);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultList = this.engine.queryList(oqlStmt, new HashMap<>());
        Assert.assertEquals(2, resultList.size());
        JsonTestUtils.assertObjectEquals(oqlInfo.resultMaps, resultList);
    }

}
