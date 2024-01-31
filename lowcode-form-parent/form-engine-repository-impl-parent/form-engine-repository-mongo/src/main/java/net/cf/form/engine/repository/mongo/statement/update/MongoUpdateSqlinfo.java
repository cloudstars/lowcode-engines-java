package net.cf.form.engine.repository.mongo.statement.update;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.mongo.statement.AbstractQueryableSqlInfo;
import net.cf.form.engine.repository.mongo.statement.insert.MongoColumnDefinition;
import net.cf.form.engine.repository.mongo.statement.insert.MongoColumnValues;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoUpdateSqlinfo  extends AbstractQueryableSqlInfo<MongoUpdateSqlinfo> {

    public MongoUpdateSqlinfo(DataObject dataObject) {
        super(dataObject);
    }


    private final List<MongoColumnDefinition> columns = new ArrayList<>();

    private final List<MongoColumnValues> valuesList = new ArrayList<>();


    private Document whereDoc = new Document();


    public List<MongoColumnDefinition> getColumns() {
        return columns;
    }

    public List<MongoColumnValues> getValuesList() {
        return valuesList;
    }

}
