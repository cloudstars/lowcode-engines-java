package net.cf.object.engine.oql.testcase.select;

import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.Map;

public abstract class AbstractSelectExpenseAggregateRepoTest
        extends AbstractOqlRepoTest
        implements SelectExpenseAggregateTest {

    protected AbstractSelectExpenseAggregateRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Expense.json"};
    }

    @Override
    public void testSelectExpenseCount() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPENSE_COUNT);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }

    @Override
    public void testSelectExpenseSum() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPENSE_SUM);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }

    @Override
    public void testSelectExpenseAvg() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPENSE_AVG);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }

    @Override
    public void testSelectExpenseMax() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPENSE_MAX);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }

    @Override
    public void testSelectExpenseMin() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXPENSE_MIN);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }
}
