
package net.cf.object.engine.oql.testcase.update.detail;

/**
 * 测试只更新子表，不更新主表的案例
 *
 * @author clouds
 */
public interface UpdateDetailOnlyTest {

    String OQL_FILE_PATH = "oql/update/detail/UpdateDetailOnly.json";

    String OQL_UPDATE_DETAIL_ONLY_WITH_DOT_VARS = "UpdateDetailOnlyWithDotVars";

    void testUpdateDetailOnlyWithDotVars();

}