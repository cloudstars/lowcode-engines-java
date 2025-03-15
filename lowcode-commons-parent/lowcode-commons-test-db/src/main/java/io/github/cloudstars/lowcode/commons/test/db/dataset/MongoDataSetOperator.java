package io.github.cloudstars.lowcode.commons.test.db.dataset;

import org.bson.Document;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MongoDataSetOperator implements IDataSetOperator {

    private MongoTemplate mongoTemplate;

    public MongoDataSetOperator(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void setUp(IDataSet dataSet) {
        String[] collectionNames = dataSet.getTableNames();
        for (String collectionName : collectionNames) {
            ITable table = dataSet.getTable(collectionName);
            Collection<Document> documents = this.convertToDocuments(table);
            this.mongoTemplate.insert(documents, collectionName);
        }
    }

    private Collection<Document> convertToDocuments(ITable table) {
        List<Document> documents = new ArrayList<>();
        Column[] columns = table.getTableMetaData().getColumns();
        int cl = columns.length;
        int rc = table.getRowCount();
        for (int i = 0; i < rc; i++) {
            Document document = new Document();
            for (int j = 0; j < cl; j++) {
                Column column = columns[j];
                String columnName = column.getColumnName();
                Object value = table.getValue(i, columnName);
                if (column.isAuto() && value == null) {
                    document.put(columnName, new ObjectId());
                } else if (column.isAuto() && value != null) {
                    document.put(columnName, new ObjectId(String.valueOf(value)));
                } else if (column.getDataType() == DataType.DECIMAL) {
                    document.put(columnName, new Decimal128((BigDecimal) value));
                } else {
                    document.put(columnName, value);
                }
            }
            documents.add(document);
        }

        return documents;
    }

    @Override
    public void tearDown(IDataSet dataSet) {
        String[] collectionNames = dataSet.getTableNames();
        for (String collectionName : collectionNames) {
            this.mongoTemplate.dropCollection(collectionName);
        }
    }
}
