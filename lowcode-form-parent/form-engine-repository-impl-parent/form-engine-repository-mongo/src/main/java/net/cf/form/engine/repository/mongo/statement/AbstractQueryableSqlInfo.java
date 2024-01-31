package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;
import org.bson.Document;

/**
 * 抽象的有查询功能的SQL信息
 *
 * @author clouds
 */
public abstract class AbstractQueryableSqlInfo<T extends AbstractQueryableSqlInfo> extends AbstractSqlInfo<T> {


    private Document whereDoc = new Document();

    public AbstractQueryableSqlInfo(DataObject dataObject) {
        super(dataObject);
    }


    public void setWhere(Document document) {
        this.whereDoc = document;
    }

    public Document getWhereDoc() {
        return this.whereDoc;
    }
}
