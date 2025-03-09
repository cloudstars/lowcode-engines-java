package net.cf.object.engine.oql.testcase.select;

import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.HashMap;
import java.util.Map;

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
        // H2 不支持 JSON_CONTAINS，需要连接 MySQL 测试
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_BY_HOBBY);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, new HashMap<>());
        assert (DataCompareTestUtils.equalsMap(resultMap, oqlInfo.resultMaps.get(0)));
    }

    @Override
    public void testSelectStaffByNotHobby() {
        // H2 不支持 JSON_CONTAINS，需要连接 MySQL 测试
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_BY_NOT_HOBBY);
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, new HashMap<>());
        assert (DataCompareTestUtils.equalsMap(resultMap, oqlInfo.resultMaps.get(0)));
    }
}
