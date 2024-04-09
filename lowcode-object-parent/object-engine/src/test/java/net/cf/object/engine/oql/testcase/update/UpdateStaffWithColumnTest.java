package net.cf.object.engine.oql.testcase.update;

/**
 * 更新员工的更新人为创建人测试接口
 *
 * @author clouds
 */
public interface UpdateStaffWithColumnTest {

    String OQL_FILE_PATH = "oql/update/UpdateStaffWithColumn.json";

    String OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR = "UpdateStaffModifierWithCreator";

    String OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR_VARS = "UpdateStaffModifierWithCreatorVars";

    void testUpdateStaffModifierWithCreator();

    void testUpdateStaffModifierWithCreatorVars();

}