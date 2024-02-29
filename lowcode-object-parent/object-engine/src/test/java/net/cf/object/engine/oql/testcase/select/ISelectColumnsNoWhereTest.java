package net.cf.object.engine.oql.testcase.select;

public interface ISelectColumnsNoWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectColumnsNoWhere.json";

    String OQL_SELECT_TRAVEL_LIST = "SelectTravelList";

    /**
     * 测试查询出差列表
     *
     */
    void testSelectTravelList();
}