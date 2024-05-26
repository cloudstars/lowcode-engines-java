package net.cf.object.engine.oql.testcase.select;

/**
 * 测试 where条件 [not] like
 *
 * @author clouds
 */
public interface SelectWhereLikeTest {

    String OQL_FILE_PATH = "oql/select/SelectWhereLike.json";

    String OQL_SELECT_LIKE = "SelectWhereLike";

    String OQL_SELECT_NOT_LIKE = "SelectWhereNotLike";

    void testSelectWhereLike();

    void testSelectWhereNotLike();

}