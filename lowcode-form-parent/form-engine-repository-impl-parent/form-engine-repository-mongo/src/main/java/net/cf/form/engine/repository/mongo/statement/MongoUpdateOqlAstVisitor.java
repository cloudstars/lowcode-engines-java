package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObjectResolver;

public class MongoUpdateOqlAstVisitor extends AbstractOqlAstVisitor {

    public MongoUpdateOqlAstVisitor(DataObjectResolver resolver) {
        super(resolver);
    }
}
