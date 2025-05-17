package io.github.cloudstars.object.engine;

import java.util.List;

public class ObjectEngineImpl implements ObjectEngine {

    @Override
    public int insert(XObject object) {
        return 0;
    }

    @Override
    public int[] batchInsert(List<XObject> objects) {
        return new int[0];
    }

    @Override
    public int update(XObject object) {
        return 0;
    }

    @Override
    public int[] batchUpdate(List<XObject> objects) {
        return new int[0];
    }


}
