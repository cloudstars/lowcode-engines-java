package net.cf.object.engine.oql.testcase.select.lookup;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectStaffLookupHobbyMultiRefRepoTest
        extends AbstractOqlRepoTest
        implements SelectStaffLookupHobbyMultiRefTest {

    @Resource
    private OqlEngine engineNew;

    protected AbstractSelectStaffLookupHobbyMultiRefRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json", "dataset/Hobby.json"};
    }

    @Override
    public void testSelectStaffLookupHobby() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_LOOKUP_HOBBY);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engineNew.queryList(stmt);
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }

}

