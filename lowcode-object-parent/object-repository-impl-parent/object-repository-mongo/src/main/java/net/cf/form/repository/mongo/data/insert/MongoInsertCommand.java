package net.cf.form.repository.mongo.data.insert;


import net.cf.form.repository.mongo.data.AbstractMongoCommand;
import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MongoInsertCommand extends AbstractMongoCommand {

    private String collectionName;

    private List<Document> documents;


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }


    public String getSqlExpr() {
        String format = "db.getCollection('%s').insertMany([%s])";
        String command = String.join(",", documents.stream().map(document -> document.toJson()).collect(Collectors.toList()));
        return String.format(format, this.collectionName, command);
    }
}
