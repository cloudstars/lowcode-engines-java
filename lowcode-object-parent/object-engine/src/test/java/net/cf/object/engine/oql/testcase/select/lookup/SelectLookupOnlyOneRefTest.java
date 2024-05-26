package net.cf.object.engine.oql.testcase.select.lookup;

/**
 * 查询报销表关联的出差申请单，且未查任何报销表信息
 *
 * @author clouds
 */
public interface SelectLookupOnlyOneRefTest {

    String OQL_FILE_PATH = "oql/select/lookup/SelectLookupOnlyOneRef.json";

    String OQL_SELECT_ALL_FIELDS = "SelectAllFields";

    String OQL_SELECT_MULTI_FIELDS = "SelectMultiFields";

    String OQL_SELECT_EXPAND_FIELDS = "SelectExpandFields";


    void testSelectAllFields();

    void testSelectMultiFields();

    void testSelectExpandFields();

}