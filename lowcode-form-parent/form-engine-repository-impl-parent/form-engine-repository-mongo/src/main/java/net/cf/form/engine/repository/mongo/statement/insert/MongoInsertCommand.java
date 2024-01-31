package net.cf.form.engine.repository.mongo.statement.insert;

import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MongoInsertCommand {

    private String collectionName;

    private List<Document> documents;


    public MongoInsertCommand(String collectionName, List<Document> documents) {
        this.collectionName = collectionName;
        this.documents = documents;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public List<Document> getDocuments() {
        return documents;
    }


    private static String INSERT_COMMAND = "db.getCollection('%s').insertMany([%s])";
    public String getSqlExpr() {
        String command = String.join(",", this.documents.stream().map(document -> document.toJson()).collect(Collectors.toList()));
        return String.format(INSERT_COMMAND, this.collectionName, command);
    }
}
