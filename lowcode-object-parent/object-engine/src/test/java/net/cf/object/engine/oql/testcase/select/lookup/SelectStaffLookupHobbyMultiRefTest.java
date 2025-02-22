package net.cf.object.engine.oql.testcase.select.lookup;

/**
 * 查询员工关联兴趣爱好（一对多）
 *
 * @author clouds
 */
public interface SelectStaffLookupHobbyMultiRefTest {

    String OQL_FILE_PATH = "oql/select/lookup/SelectStaffHobbyMultiRef.json";

    String OQL_SELECT_STAFF_LOOKUP_HOBBY = "SelectStaffLookupHobby";

    void testSelectStaffLookupHobby();

}