package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;

public abstract class AbstractOqlInfo {

    private XObject object;

    public XObject getObject() {
        return object;
    }

    public void setObject(XObject object) {
        this.object = object;
    }
}
