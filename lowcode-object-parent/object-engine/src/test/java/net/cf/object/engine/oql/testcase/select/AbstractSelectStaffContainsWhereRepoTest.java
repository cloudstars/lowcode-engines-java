package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

public abstract class AbstractSelectStaffContainsWhereRepoTest
        extends AbstractOqlRepoTest
        implements SelectStaffContainsWhereTest {

    protected AbstractSelectStaffContainsWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json", "dataset/Hobby.json"};
    }


    @Override
    public void testSelectStaffByHobby() {
        //todo H2 不支持 JSON_CONTAINS
//        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_BY_HOBBY);
//        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
//        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, new HashMap<>());
    }

}
