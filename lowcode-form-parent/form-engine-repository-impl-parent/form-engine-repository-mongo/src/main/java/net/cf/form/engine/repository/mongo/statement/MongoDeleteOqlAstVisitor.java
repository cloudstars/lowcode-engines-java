package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObjectResolver;

public class MongoDeleteOqlAstVisitor extends AbstractOqlAstVisitor {

    public MongoDeleteOqlAstVisitor(DataObjectResolver resolver) {
        super(resolver);
    }
}
