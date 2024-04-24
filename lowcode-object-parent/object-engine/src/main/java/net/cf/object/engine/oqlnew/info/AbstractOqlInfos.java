package net.cf.object.engine.oqlnew.info;

import net.cf.object.engine.object.XObject;

public abstract class AbstractOqlInfos {

    /**
     * 解析后的本表模型
     */
    protected XObject resolvedSelfObject;

    public XObject getResolvedSelfObject() {
        return resolvedSelfObject;
    }

    public void setResolvedSelfObject(XObject resolvedSelfObject) {
        this.resolvedSelfObject = resolvedSelfObject;
    }
}
