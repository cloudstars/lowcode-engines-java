package net.cf.object.engine.record;

import net.cf.object.engine.RecordEngine;
import net.cf.object.engine.record.bean.TravelApply;

import javax.annotation.Resource;

public class RecordInsertTest {

    @Resource
    private RecordEngine recordEngine;

    public void testInsert() {
        TravelApply apply = new TravelApply();
        apply.setEnterpriseKey("ekey");
        apply.setApplyName("aname");

        //XObject object = objectResolver.resolveObject("TravelApply");
        //this.recordEngine.createOne(object, apply);
    }

}
