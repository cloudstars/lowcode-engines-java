package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，带有简单条件的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfSimpleWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSimpleWhere.json";

    String OQL_SELECT_TRAVEL_LIST = "SelectTravelList";

    /**
     * 测试查询出差列表
     *
     */
    void testSelectTravelList();
}