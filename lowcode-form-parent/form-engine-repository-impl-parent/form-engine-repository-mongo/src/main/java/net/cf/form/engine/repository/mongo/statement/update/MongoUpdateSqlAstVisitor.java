package net.cf.form.engine.repository.mongo.statement.update;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.data.value.DataType;
import net.cf.form.engine.repository.mongo.statement.AbstractOqlAstVisitor;
import net.cf.form.engine.repository.mongo.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.mongo.statement.Printer;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlNotExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于生成 MySql insert 语句的AST访问器
 *
 * @author clouds
 */
public final class MongoUpdateSqlAstVisitor extends AbstractOqlAstVisitor implements OqlAstVisitor {

    private Printer printer = new Printer();

    /**
     * 当前插入的对象
     */
    protected DataObject resolvedDataObject;

    /**
     * 主表的更新信息
     */
    private  MongoUpdateSqlinfo sqlInfo;


    public MongoUpdateSqlAstVisitor(DataObjectResolver resolver) {
        super(resolver);
    }

    /**
     * 获取主表的插入语句
     *
     * @return
     */
//    public MongoUpdateCommand getSql() {
//        MongoUpdateBuilder builder = new MongoUpdateBuilder(sqlInfo);
//        return builder.buildSql();
//    }


    @Override
    public boolean visit(OqlUpdateStatement x) {
        OqlObjectSource objectSource = x.getObjectSource();
        if (objectSource instanceof OqlExprObjectSource) {
            String objectName = ((OqlExprObjectSource) objectSource).getExpr().getName();
            DataObject resolvedDataObject = resolver.resolveObject(objectName);
            assert (resolvedDataObject != null);
            this.resolvedDataObject = resolvedDataObject;
            this.sqlInfo = new MongoUpdateSqlinfo(this.resolvedDataObject);
        }

        if (x.getWhereClause() != null) {
            OqlExprAstVisitor visitor = new OqlExprAstVisitor(this.resolvedDataObject, x.getWhereClause(), this.sqlInfo);
            visitor.visit();
        }

        return true;
    }


    /**
     * 解析值
     * @param qqlExpr
     * @return
     */
    private Object analyseOqlExpr(QqlExpr qqlExpr) {
        if (qqlExpr instanceof OqlJsonObjectExpr) {
            return analyseJsonObject((OqlJsonObjectExpr) qqlExpr);
        } else if (qqlExpr instanceof OqlListExpr) {
            return analyseList((OqlListExpr) qqlExpr);
        } else if (qqlExpr instanceof QqlValuableExpr) {
            return ((QqlValuableExpr) qqlExpr).getValue();
        }
        return null;

    }

    private Object analyseJsonObject(OqlJsonObjectExpr value) {
        Map<String, Object> res = new HashMap<>();

        for (Map.Entry<String, QqlExpr> entry : value.getItems().entrySet()) {
            QqlExpr qqlExpr = entry.getValue();
            res.put(entry.getKey(), analyseOqlExpr(qqlExpr));
        }
        return res;
    }


    private Object analyseList(OqlListExpr expr) {
        List<Object> res = new ArrayList<>();
        for (QqlExpr qqlExpr : expr.getItems()) {
            res.add(analyseOqlExpr(qqlExpr));
        }
        return res;
    }




    private void visitExpr(QqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == OqlCharExpr.class) {
            this.visit((OqlCharExpr) x);
        } else if (clazz == OqlBooleanExpr.class) {
            this.visit((OqlBooleanExpr) x);
        } else if (clazz == OqlIntegerExpr.class) {
            this.visit((OqlIntegerExpr) x);
        } else if (clazz == OqlNumberExpr.class) {
            this.visit((OqlNumberExpr) x);
        } else if (clazz == OqlNullExpr.class) {
            this.visit((OqlNullExpr) x);
        } else if (clazz == OqlJsonObjectExpr.class) {
            this.visit((OqlJsonObjectExpr) x);
        } else if (clazz == OqlJsonArrayExpr.class) {
            this.visit((OqlJsonArrayExpr) x);
        } else if (clazz == OqlIdentifierExpr.class) {
            this.visit((OqlIdentifierExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            this.visit((OqlPropertyExpr) x);
        } else if (clazz == OqlNotExpr.class) {
            this.visit((OqlNotExpr) x);
        } else if (clazz == OqlBinaryOpExpr.class) {
            this.visit((OqlBinaryOpExpr) x);
        } else if (clazz == OqlMethodInvokeExpr.class) {
            this.visit((OqlMethodInvokeExpr) x);
        } else {
            x.accept(this);
        }
    }


    /**
     * 根据OQL表达式类型获取驱动层的值类型
     *
     * @param expr
     * @return
     */
    private DataType getExprValueType(QqlExpr expr) {
        DataType valueType = null;
        if (expr instanceof OqlBooleanExpr) {
            valueType = DataType.BOOLEAN;
        } else if (expr instanceof OqlTextLiteralExpr) {
            valueType = DataType.TEXT;
        } else if (expr instanceof OqlIntegerExpr) {
            valueType = DataType.INTEGER;
        } else if (expr instanceof OqlJsonObjectExpr) {
            valueType = DataType.OBJECT;
        }

        return valueType;
    }
}
