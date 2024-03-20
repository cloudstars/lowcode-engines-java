package net.cf.form.repository.mongo.data.delete;

import net.cf.form.repository.mongo.data.AbstractMongoCommand;
import org.bson.Document;

public class MongoDeleteCommand extends AbstractMongoCommand {

    private String collectionName;

    private Document whereDoc;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Document getWhereDoc() {
        return whereDoc;
    }

    public void setWhereDoc(Document whereDoc) {
        this.whereDoc = whereDoc;
    }


    public String getSqlExpr() {
        String format = "db.getCollection('%s').deleteMany([%s])";
        return String.format(format, this.collectionName, whereDoc.toJson());
    }

}
