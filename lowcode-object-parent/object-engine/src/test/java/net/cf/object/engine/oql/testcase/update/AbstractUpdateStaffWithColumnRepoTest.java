package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateStaffWithColumnRepoTest extends AbstractOqlRepoTest implements UpdateStaffWithColumnTest {

    protected AbstractUpdateStaffWithColumnRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testUpdateStaffModifierWithCreator() {

    }

    @Override
    public void testUpdateStaffModifierWithCreatorVars() {

    }
}
