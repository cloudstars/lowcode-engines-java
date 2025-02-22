package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入兴趣爱好记录
 *
 * @author clouds
 */
public interface InsertHobbyTest {

    String OQL_FILE_PATH = "oql/insert/InsertHobby.json";

    String OQL_INSERT_HOBBY = "InsertHobby";

    String OQL_INSERT_HOBBY_VARS = "InsertHobbyVars";

    void testInsertHobby();

    void testInsertHobbyVars();

}


