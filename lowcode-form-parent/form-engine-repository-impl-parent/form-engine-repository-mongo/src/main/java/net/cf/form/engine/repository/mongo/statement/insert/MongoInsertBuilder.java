package net.cf.form.engine.repository.mongo.statement.insert;

import net.cf.form.engine.repository.mongo.convert.MongoConvertManager;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoInsertBuilder {

    private MongoInsertSqlInfo sqlInfo;

    public MongoInsertBuilder(MongoInsertSqlInfo sqlInfo) {
        this.sqlInfo = sqlInfo;

    }

    private List<Document> documents;


    /**
     * 获取插入的SQL语句
     *
     * @return
     */
    public MongoInsertCommand buildSql() {
        build();
        MongoInsertCommand command = new MongoInsertCommand(sqlInfo.getDataObject().getTableName(), this.documents);
        return command;
    }

    private void build() {
        this.documents = new ArrayList<>();
        int columnSize = 0;
        List<String> cols = new ArrayList<>();

        for (MongoColumnDefinition column : sqlInfo.getColumns()) {
            cols.add(column.getName());
        }

        for (MongoColumnValues values : sqlInfo.getValuesList()) {
            if (values.getList().size() != cols.size()) {
                throw new RuntimeException("插入数据的数量与列的数据不匹配");
            }
            int i = 0;
            Document data = new Document();
            for (BaseColumnValue columnValue : values.getList()) {
                // 先不处理list
                if (columnValue instanceof MongoColumnValue) {
                    MongoColumnValue mongoColumnValue = (MongoColumnValue) columnValue;
                    if (mongoColumnValue.getValue() == null) {
                        data.put(cols.get(i), null);
                    } else {
                        data.put(cols.get(i), MongoConvertManager.getConverter(mongoColumnValue.getValueType()).convertValue(mongoColumnValue.getValue()));
                    }
                }
                i++;
            }
            this.documents.add(data);
        }
    }

}
