package net.cf.object.engine.oql.testcase.update;

/**
 * 更新员工的更新人为创建人测试接口
 *
 * @author clouds
 */
public interface UpdateStaffWithColumnTest {

    String OQL_FILE_PATH = "oql/update/UpdateStaffWithColumn.json";

    String OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA = "UpdateStaffDescrWithNameRela";

    String OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA_VARS = "UpdateStaffDescrWithNameRelaVars";

    String OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR = "UpdateStaffModifierWithCreator";

    String OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR_VARS = "UpdateStaffModifierWithCreatorVars";

    String OQL_UPDATE_STAFF_AGE_BY_ID_VARS = "UpdateStaffAgeByIdVars";

    void testUpdateStaffDescrWithNameRela();

    void testUpdateStaffDescrWithNameRelaVars();

    void testUpdateStaffModifierWithCreator();

    void testUpdateStaffModifierWithCreatorVars();

    void testUpdateStaffAgeByIdVars();
}