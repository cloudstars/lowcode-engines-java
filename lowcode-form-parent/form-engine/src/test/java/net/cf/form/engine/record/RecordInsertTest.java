package net.cf.form.engine.record;

import net.cf.form.engine.ObjectRecordEngine;
import net.cf.form.engine.XObjectResolver;
import net.cf.form.engine.object.TravelApply;
import net.cf.form.engine.object.XObject;

import javax.annotation.Resource;

public class RecordInsertTest {

    @Resource
    private ObjectRecordEngine recordEngine;

    @Resource
    private XObjectResolver objectResolver;

    public void testInsert() {
        TravelApply apply = new TravelApply();
        apply.setEnterpriseKey("ekey");
        apply.setApplyName("aname");

        XObject object = objectResolver.resolveObject("TravelApply");
        this.recordEngine.createOne(object, apply);
    }

}
