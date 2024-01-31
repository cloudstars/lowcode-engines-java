package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;

import java.util.HashMap;
import java.util.Map;

public class ObjectContextPool {

    private Map<String, DataObject> dataObjects = new HashMap<>();

    private Map<String, ObjectContext> objectContexts = new HashMap<>();

    private String currentObject;


    public Map<String, DataObject> getDataObjects() {
        return dataObjects;
    }

    public void setDataObjects(Map<String, DataObject> dataObjects) {
        this.dataObjects = dataObjects;
    }

    public Map<String, ObjectContext> getObjectContexts() {
        return objectContexts;
    }

    public void setObjectContexts(Map<String, ObjectContext> objectContexts) {
        this.objectContexts = objectContexts;
    }

    public ObjectContext getCurrentObject() {
        return objectContexts.get(this.currentObject);
    }

    public ObjectContext getObjectContext(String objectName) {
        return objectContexts.get(objectName.toUpperCase());
    }

    public DataObject setCurrentObject(String objectName) {
        DataObject dataObject = dataObjects.get(objectName);
        this.currentObject = objectName.toUpperCase();
        return dataObject;
    }


    public void resolveObject(DataObject dataObject) {
        String objectName = dataObject.getName().toUpperCase();
        if (dataObjects.containsKey(objectName)) {
            return;
        }

        dataObjects.put(objectName, dataObject);
        if (!objectContexts.containsKey(objectName)) {
            objectContexts.put(objectName, new ObjectContext(dataObject));
        }
    }
}
