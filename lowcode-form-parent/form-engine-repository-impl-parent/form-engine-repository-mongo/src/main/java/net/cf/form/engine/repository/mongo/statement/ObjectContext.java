package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;

public class ObjectContext {

    private DataObject mainDataObject;

    public ObjectContext(DataObject mainDataObject) {
        this.mainDataObject = mainDataObject;
    }


    public DataObject getMainDataObject() {
        return this.mainDataObject;
    }

}
