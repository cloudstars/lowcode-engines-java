package net.cf.form.repository.mongo.data.update;

import net.cf.form.repository.mongo.data.AbstractMongoCommand;
import org.bson.Document;

public class MongoUpdateCommand extends AbstractMongoCommand {

    private String collectionName;

    private Document setDoc;

    private Document whereDoc;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Document getSetDoc() {
        return setDoc;
    }

    public void setSetDoc(Document setDoc) {
        this.setDoc = setDoc;
    }

    public Document getWhereDoc() {
        return whereDoc;
    }

    public void setWhereDoc(Document whereDoc) {
        this.whereDoc = whereDoc;
    }


    public String getSqlExpr() {
        Document document = new Document("multi", true);
        String format = "db.getCollection('%s').update(%s,[%s], %s)";
        return String.format(format, this.collectionName, whereDoc.toJson(), setDoc.toJson(), document.toJson());
    }

}
