package net.cf.form.repository.mongo.data.visitor;

import net.cf.form.repository.mongo.data.MongoDataConverter;
import net.cf.form.repository.mongo.data.select.JoinInfo;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.AbstractSqlNumericLiteralExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlDecimalExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlExistsExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * todo 整个expr解析逻辑还需要调整
 */
public class MongoExprVisitor {

    private static final Logger log = LoggerFactory.getLogger(MongoExprVisitor.class);

    public MongoExprVisitor() {

    }

    /**
     * 用于外部调用expr
     *
     * @return
     */
    public static Object visit(SqlExpr sqlExpr) {
        return visit(sqlExpr, GlobalContext.getDefault());
    }

    /**
     * 用于外部调用解析expr
     *
     * @return
     */
    public static Object visit(SqlExpr sqlExpr, GlobalContext globalContext) {
        return analyse(sqlExpr, globalContext, VisitContext.getDefaultContextInfo());
    }

    /**
     * 用于其他visitor嵌套调用
     *
     * @param sqlExpr
     * @param globalContext
     * @param innerContext
     * @return
     */
    public static Object visit(SqlExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        return analyse(sqlExpr, globalContext, innerContext);
    }

    /**
     * expr解析
     *
     * @param sqlExpr
     * @param globalContext
     * @param visitContext
     * @return
     */
    private static Object analyse(SqlExpr sqlExpr, GlobalContext globalContext, VisitContext visitContext) {
        // 函数
        if (sqlExpr instanceof SqlMethodInvokeExpr) {
            return MongoMethodExprVisitor.build((SqlMethodInvokeExpr) sqlExpr, globalContext, visitContext);
        }

        // 表达式
        // like todo
        if (sqlExpr instanceof SqlLikeOpExpr) {
            return MongoExpressionVisitor.visitLike((SqlLikeOpExpr) sqlExpr, globalContext);
        }
        // in
        if (sqlExpr instanceof SqlInListExpr) {
            return MongoExpressionVisitor.visitIn((SqlInListExpr) sqlExpr, globalContext);
        }
        // 操作符
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            return MongoExpressionVisitor.visitBinary((SqlBinaryOpExpr) sqlExpr, globalContext, visitContext);
        }

        // 字段相关
        if (sqlExpr instanceof SqlIdentifierExpr) {
            return visitIdentify((SqlIdentifierExpr) sqlExpr, globalContext, visitContext);
        }
        if (sqlExpr instanceof SqlPropertyExpr) {
            return visitProperty((SqlPropertyExpr) sqlExpr, globalContext, visitContext);
        }

        // 值相关
        if (sqlExpr instanceof SqlValuableExpr) {
            return visitValue((SqlValuableExpr) sqlExpr, globalContext, visitContext);
        }
        if (sqlExpr instanceof SqlVariantRefExpr) {
            return visitVariable((SqlVariantRefExpr) sqlExpr, globalContext, visitContext);
        }

        // 子查询
        if (sqlExpr instanceof SqlExistsExpr) {
            return visitSqlExistsExpr((SqlExistsExpr) sqlExpr, globalContext, visitContext);
        }


        log.error("not support, ", sqlExpr);
        throw new RuntimeException("not support");
    }


    /**
     * @param sqlExpr
     * @param globalContext
     * @param innerContext
     * @return
     */
    private static Object visitIdentify(SqlIdentifierExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        String field = sqlExpr.getName();
        if (innerContext != null && innerContext.isFieldTag()) {
            return "$" + field;
        } else if (globalContext != null && globalContext.getPositionEnum() == PositionEnum.VALUE) {
            return "$" + field;
        }
        return field;
    }

    /**
     * @param sqlExpr
     * @param globalContext
     * @param innerContext
     * @return
     */
    private static Object visitValue(SqlValuableExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        if (innerContext != null && innerContext.isAutoGen()) {
            if (sqlExpr instanceof SqlCharExpr) {
                return MongoDataConverter.convertObjectId(sqlExpr.getValue());
            } else {
                throw new RuntimeException("not support");
            }
        }

        if (sqlExpr instanceof SqlDecimalExpr) {
            return MongoDataConverter.convert(((SqlDecimalExpr) sqlExpr).getValue());
        } else if (sqlExpr instanceof AbstractSqlNumericLiteralExpr) {
            return MongoDataConverter.convert(((AbstractSqlNumericLiteralExpr) sqlExpr).getNumber());
        } else {
            return sqlExpr.getValue();
        }

    }

    private static Object visitVariable(SqlVariantRefExpr sqlExpr, GlobalContext globalContext, VisitContext innerContext) {
        Object value = MongoDataConverter.convertVariable(sqlExpr, globalContext.getDataMap());
        if (value == null) {
            return null;
        }
        return getVariable(value, innerContext);
    }

    /**
     * 处理SqlVariantRefExpr 和 paramMap 替换后的值，可能为单项或列表
     *
     * @param value
     * @param contextInfo
     * @return
     */
    private static Object getVariable(Object value, VisitContext contextInfo) {
        if (value == null) {
            return null;
        }
        // 如果变量塞列表
        if (value instanceof List) {
            List<Object> valList = new ArrayList<>();
            for (Object val : (List) value) {
                valList.add(getVariable(val, contextInfo));
            }
            return valList;
        }

        // 如果变量塞单项
        if (contextInfo.isAutoGen()) {
            if (value instanceof String) {
                return MongoDataConverter.convertObjectId(value);
            } else {
                throw new RuntimeException("not support");
            }
        }
        return MongoDataConverter.convert(value);
    }


    // 目前property只会有两层
    private static Object visitProperty(SqlPropertyExpr sqlPropertyExpr, GlobalContext globalContext, VisitContext contextInfo) {
        SqlName sqlName = sqlPropertyExpr.getOwner();
        String parentName = "";
        if (sqlName instanceof SqlIdentifierExpr) {
            parentName = (String) visitIdentify((SqlIdentifierExpr) sqlName, globalContext, VisitContext.getDefaultContextInfo());
        } else {
            throw new RuntimeException("NOT SUPPORT");
        }
        String properExprName = sqlPropertyExpr.getName();
        String name = parentName + "." + properExprName;
        // join替换
        if (globalContext != null && globalContext.getJoinInfo() != null) {
            name = getJoinReplaceName(parentName, name, globalContext, contextInfo);
        }
        return name;
    }

    private static String getJoinReplaceName(String parentName, String name, GlobalContext globalContext, VisitContext contextInfo) {
        // 替换
        JoinInfo joinInfo = globalContext.getJoinInfo();
        String replaceName = name;
        if (joinInfo.getTableNames().contains(parentName)) {

            if (joinInfo.getMainReplaceMap().containsKey(name)) {
                replaceName = joinInfo.getMainReplaceMap().get(name);
            } else if (joinInfo.getSlaveReplaceMap().containsKey(name)) {
                replaceName = joinInfo.getSlaveReplaceMap().get(name);
                if (contextInfo.isFieldTag()) {
                    replaceName = "$" + replaceName;
                }
            }
        }
        return replaceName;
    }


    private static Object visitSqlExistsExpr(SqlExistsExpr sqlExistsExpr, GlobalContext globalContext, VisitContext context) {
        String key = String.valueOf(sqlExistsExpr.hashCode());
        String alias = globalContext.getExistAliasMap().get(key);
        Document document;
        if (sqlExistsExpr.not) {
            document = new Document(alias, new Document("$exists", false));
        } else {
            document = new Document(alias, new Document("$exists", true));
        }
        return document;
    }


}
