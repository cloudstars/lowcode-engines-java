package net.cf.object.engine.oql.testcase.select.lookup;

import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectLookupOnlyOneRefRepoTest
        extends AbstractOqlRepoTest
        implements SelectLookupOnlyOneRefTest {

    protected AbstractSelectLookupOnlyOneRefRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Expense.json", "dataset/Travel.json"};
    }

    @Override
    public void testSelectAllFields() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_ALL_FIELDS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engine.queryList(stmt, oqlInfo.paramMap);
        assert (DataCompareTestUtils.isAssignableFromListWithProperties(oqlInfo.resultMaps, resultMaps, Arrays.asList("applyId", "applyName")));
    }

    @Override
    public void testSelectMultiFields() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_MULTI_FIELDS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engine.queryList(stmt, oqlInfo.paramMap);
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }

    @Override
    public void testSelectExpandFields() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPAND_FIELDS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engine.queryList(stmt, oqlInfo.paramMap);
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }
}
