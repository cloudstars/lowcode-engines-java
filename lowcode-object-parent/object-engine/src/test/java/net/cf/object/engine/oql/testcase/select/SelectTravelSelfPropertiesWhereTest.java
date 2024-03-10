package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，带有子属性条件的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfPropertiesWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelPropertiesWhere.json";

    String OQL_SELECT_TRAVEL_LIST_BY_CREATOR_NAME = "SelectTravelListByCreatorName";

    /**
     * 根据创建人的姓名（子属性）测试查询出差列表
     *
     */
    void testSelectTravelListByCreatorName();
}