package net.cf.object.engine.oql.testcase.select;

/**
 * 查询员工本表，测试条件中带列的比较的查询条件
 *
 * @author clouds
 */
public interface SelectStaffColumnExprWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffSelfColumnExprWhere.json";

    String OQL_SELECT_LIST_WITH_CREATOR_EQUALS_OWNER = "SelectListWithCreateEqualsOwner";

    void testSelectListWithCreateEqualsOwner();

}