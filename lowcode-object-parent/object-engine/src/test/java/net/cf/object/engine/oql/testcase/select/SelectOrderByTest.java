package net.cf.object.engine.oql.testcase.select;

/**
 * 测试 order by
 *
 * @author clouds
 */
public interface SelectOrderByTest {

    String OQL_FILE_PATH = "oql/select/SelectOrderBy.json";

    String OQL_SELECT_ORDER_BY_ASC = "SelectOrderByStaffCodeAsc";

    String OQL_SELECT_ORDER_BY_DESC = "SelectOrderByStaffCodeDesc";

    void testSelectOrderByStaffCodeAsc();

    void testSelectOrderByStaffCodeDesc();

}