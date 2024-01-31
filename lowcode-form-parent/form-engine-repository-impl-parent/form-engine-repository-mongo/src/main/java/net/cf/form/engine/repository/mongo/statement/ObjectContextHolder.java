package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;

public class ObjectContextHolder {

    private static ThreadLocal<ObjectContextPool> OBJECT_CONTEXT_POOL = new InheritableThreadLocal<>();

    public static void init() {
        OBJECT_CONTEXT_POOL.set(new ObjectContextPool());
    }

    public static void remove() {
        OBJECT_CONTEXT_POOL.remove();
    }


    public static void addObject(DataObject dataObject) {
        ObjectContextPool objectContextPool =  OBJECT_CONTEXT_POOL.get();
        objectContextPool.resolveObject(dataObject);
    }

    public static void setCurrentObjectContext(String objectName) {
        OBJECT_CONTEXT_POOL.get().setCurrentObject(objectName);
    }



    public static ObjectContext getCurrentObjectContext() {
        ObjectContextPool objectContextPool =  OBJECT_CONTEXT_POOL.get();
        return objectContextPool.getCurrentObject();
    }


    public static ObjectContextPool getPool() {
        return OBJECT_CONTEXT_POOL.get();
    }


    public static ObjectContext getObjectContext(String objectName) {
        return OBJECT_CONTEXT_POOL.get().getObjectContext(objectName);
    }


}
