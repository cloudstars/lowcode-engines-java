package net.cf.object.engine.oql.stmt;

import net.cf.object.engine.object.XObject;

public abstract class AbstractOqlInfo {

    protected XObject resolvedObject;

    public XObject getResolvedObject() {
        return resolvedObject;
    }

    public void setResolvedObject(XObject resolvedObject) {
        this.resolvedObject = resolvedObject;
    }
}
