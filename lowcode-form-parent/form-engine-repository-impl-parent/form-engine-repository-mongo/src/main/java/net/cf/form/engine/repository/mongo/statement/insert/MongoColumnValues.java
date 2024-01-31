package net.cf.form.engine.repository.mongo.statement.insert;

import java.util.ArrayList;
import java.util.List;

public class MongoColumnValues {

    private final List<BaseColumnValue> list = new ArrayList<>();

    public void addValue(BaseColumnValue value) {
        this.list.add(value);
    }

    public List<BaseColumnValue> getList() {
        return list;
    }
}
