package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.Assert;

import javax.annotation.Resource;

public class OqlInsertTest {
    private final String INSERT_TRAVEL_APPLY = "insert into TravelApply(applyName, enterpriseKey) values ('test', '123')";

    private final String INSERT_TRAVEL_APPLY_PARAMS = "insert into TravelApply(applyName, enterpriseKey) values (#{ek}, #{an})";

    @Resource
    private OqlEngine recordEngine;

    public void testInsertTravelApply() {
        OqlInsertStatement stmt = OqlUtils.parseSingleInsertStatement(INSERT_TRAVEL_APPLY);
        int effectedRows = this.recordEngine.create(stmt);
        Assert.assertTrue(effectedRows == 1);
        // TODO 要通过别的方式从DB中查出数据并作对比断言
    }
}
