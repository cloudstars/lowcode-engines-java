package net.cf.object.engine.oql.testcase.select;

/**
 * 查询报销本表，测试聚合函数
 *
 * @author clouds
 */
public interface SelectExpenseAggregateTest {

    String OQL_FILE_PATH = "oql/select/SelectExpenseAggregate.json";

    String OQL_SELECT_EXPENSE_COUNT = "SelectExpenseCount";

    String OQL_SELECT_EXPENSE_SUM = "SelectExpenseSum";

    String OQL_SELECT_EXPENSE_AVG = "SelectExpenseAvg";

    String OQL_SELECT_EXPENSE_MAX = "SelectExpenseMax";

    String OQL_SELECT_EXPENSE_MIN = "SelectExpenseMin";

    void testSelectExpenseCount();

    void testSelectExpenseSum();

    void testSelectExpenseAvg();

    void testSelectExpenseMax();

    void testSelectExpenseMin();

}