package net.cf.form.engine.repository.mongo.statement.insert;


import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.mongo.statement.AbstractSqlInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入语句的信息
 *
 * @author clouds
 */
public class MongoInsertSqlInfo extends AbstractSqlInfo<MongoInsertSqlInfo> {
    public MongoInsertSqlInfo(DataObject dataObject) {
        super(dataObject);
    }


    private final List<MongoColumnDefinition> columns = new ArrayList<>();

    private final List<MongoColumnValues> valuesList = new ArrayList<>();




    public List<MongoColumnDefinition> getColumns() {
        return columns;
    }

    public void addColumn(MongoColumnDefinition column) {
        this.columns.add(column);
    }

    public List<MongoColumnValues> getValuesList() {
        return valuesList;
    }

    public void addValues(MongoColumnValues values) {
        this.valuesList.add(values);
    }


}
