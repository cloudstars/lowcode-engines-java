package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * OQL 表达式解析器
 *
 * @author clouds
 */
public class OqlExprParser extends SqlExprParser {

    protected CachedObjectResolverProxy resolver;

    protected Set<String> supportedMethodNames = new HashSet<>();

    public OqlExprParser(XObjectResolver resolver, String oql) {
        super(oql);
        this.resolver = new CachedObjectResolverProxy(resolver);
        this.supportedMethodNames.addAll(Arrays.asList("NOW", "LTRIM", "CONCAT"));
    }

    /**
     * 解析模型源
     *
     * @param tableSource
     * @return
     */
    protected OqlObjectSource parseObjectSource(SqlTableSource tableSource) {
        OqlObjectSource objectSource = null;
        if (tableSource instanceof SqlExprTableSource) {
            objectSource = this.parseExprObjectSource((SqlExprTableSource) tableSource);
        }

        return objectSource;
    }

    /**
     * 解析标识符模型源
     *
     * @param exprTableSource
     * @return
     */
    protected OqlExprObjectSource parseExprObjectSource(SqlExprTableSource exprTableSource) {
        String objectName = exprTableSource.getTableName();
        XObject resolvedObject = this.resolver.resolve(objectName);
        if (resolvedObject == null) {
            throw new FastOqlException("模型" + objectName + "不存在！");
        }

        OqlExprObjectSource exprObjectSource = new OqlExprObjectSource(objectName);
        exprObjectSource.setResolvedObject(resolvedObject);
        return exprObjectSource;
    }

    /**
     * 将一个SQL层的表达式转为OQL层的表达式
     * <p>
     * 大部分表达式会还样返回，部分和模型、字段相关的表达式会提升为OQL特殊的表达式
     *
     * @param object 当前模型（区别于selfObject，selfObject是指OQL语中的objectSource中的模型）
     * @param x
     * @return
     */
    protected SqlExpr parseSqlExpr(final XObject object, final SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlIdentifierExpr.class) {
            return this.parseIdentifierExpr(object, (SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            return this.parsePropertyExpr(object, (SqlPropertyExpr) x);
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.parseLikeExpr(object, (SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.parseInListExpr(object, (SqlInListExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.parseBinaryOpExpr(object, (SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.parseBinaryOpExprGroup(object, (SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.parseAggregateExpr(object, (SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.parseMethodInvokeExpr(object, (SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 解析标识符表达式
     *
     * @param object
     * @param expr
     * @return
     */
    private SqlExpr parseIdentifierExpr(XObject object, SqlIdentifierExpr expr) {
        String fieldName = expr.getName();
        XField field = object.getField(fieldName);
        if (field == null) {
            String objectName = fieldName;
            XObject refObject = this.resolver.resolve(objectName);
            if (refObject == null) {
                throw new FastOqlException("字段" + fieldName + "不存在！");
            } else {
                OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(objectName);
                objectExpandExpr.setDefaultExpanded(true);
                XObjectRefField objectRefField = object.getObjectRefField(objectName);
                objectExpandExpr.setResolvedObjectRefField(objectRefField);
                objectExpandExpr.setResolvedRefObject(refObject);
                return objectExpandExpr;
            }
        }

        // 如果字段下存在子属性的话，那么转换为字段展开表达式（默认展开）
        List<XProperty> properties = field.getProperties();
        if (properties != null && properties.size() > 0) {
            OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(fieldName);
            fieldExpandExpr.setDefaultExpanded(true);
            fieldExpandExpr.setResolvedField(field);
            return fieldExpandExpr;
        } else {
            OqlFieldExpr fieldExpr = new OqlFieldExpr(null, fieldName);
            fieldExpr.setResolvedField(field);
            return fieldExpr;
        }
    }

    /**
     * 解析属性表达式
     *
     * @param object
     * @param x
     * @return
     */
    private SqlExpr parsePropertyExpr(XObject object, SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        String fullName = x.toString();
        SqlExpr sqlX = x;
        if (owner instanceof SqlPropertyExpr) { // 三级属性处理，如：object.field.property
            SqlPropertyExpr propOwner = (SqlPropertyExpr) owner;
            SqlExpr ownerOwner = propOwner.getOwner();
            if (ownerOwner instanceof SqlPropertyExpr) {
                throw new FastOqlException("不支持多级属性：" + fullName + "，最多支持三级，如：a.b.c");
            } else if (ownerOwner instanceof SqlVariantRefExpr) {
                throw new FastOqlException("属性表达式中不支持变量：" + fullName);
            }

            SqlIdentifierExpr idOwnerOwner = (SqlIdentifierExpr) ownerOwner;
            String ownerObjectName = idOwnerOwner.getName();
            XObject resolvedOwnerObject = this.resolver.resolve(ownerObjectName);
            if (resolvedOwnerObject == null) {
                throw new FastOqlException(x + "中的模型：" + ownerObjectName + "不存在");
            }

            String ownerFieldName = propOwner.getName();
            XField resolvedOwnerField = resolvedOwnerObject.getField(ownerFieldName);
            if (resolvedOwnerField == null) {
                throw new FastOqlException("模型：" + ownerObjectName + "中的字段" + ownerFieldName + "不存在");
            }

            String ownerPropertyName = x.getName();
            XProperty resolvedOwnerProperty = resolvedOwnerField.getProperty(ownerPropertyName);
            if (resolvedOwnerProperty == null) {
                throw new FastOqlException("模型：" + ownerObjectName + "中的字段" + resolvedOwnerField + "下的属性" + ownerPropertyName + "不存在");
            }

            OqlFieldExpr ownerFieldExpr = new OqlFieldExpr(idOwnerOwner, ownerFieldName);
            ownerFieldExpr.setResolvedField(resolvedOwnerField);
            OqlPropertyExpr propExpr = new OqlPropertyExpr(ownerFieldExpr, ownerPropertyName);
            propExpr.setResolvedProperty(resolvedOwnerProperty);

            sqlX = propExpr;
        } else {
            sqlX = this.parseSimplePropertyExpr(object, x);
        }

        return sqlX;
    }

    /**
     * 解析简单的属性表达式，即不含嵌套的属性，如a.b.c就是含嵌套的，a.c就是不含嵌套的
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseSimplePropertyExpr(XObject object, SqlPropertyExpr x) {
        SqlExpr sqlX = x;
        String objectName = object.getName();

        // 校验模型的存在性
        SqlName owner = x.getOwner();
        String ownerObjectName = owner.getName();
        XObject ownerObject = this.resolver.resolve(ownerObjectName);
        if (ownerObject == null) { // 当作本模型的字段处理
            String fieldName = ownerObjectName;
            XField ownerField = object.getField(fieldName);
            // 校验字段的存在性
            if (ownerField == null) {
                throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "不存在");
            }

            String propName = x.getName();
            if (Token.STAR.getName().equals(propName)) {
                // field.* 识别为字段展开表达式
                OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(propName);
                fieldExpandExpr.setStarExpanded(true);
                fieldExpandExpr.setResolvedField(ownerField);
                sqlX = fieldExpandExpr;
            } else {
                // 校验字段属性的存在性
                XProperty property = ownerField.getProperty(propName);
                if (property == null) {
                    throw new FastOqlException("字段" + fieldName + "下的属性" + propName + "不存在！");
                }

                // 识别为字段属性表达式
                OqlFieldExpr fieldExpr = new OqlFieldExpr(null, fieldName);
                OqlPropertyExpr propExpr = new OqlPropertyExpr(fieldExpr, propName);
                propExpr.setResolvedProperty(property);
                sqlX = propExpr;
            }
        } else { // 当作其它的模型处理
            XObjectRefField objectRefField = null;
            if (!objectName.equals(ownerObject.getName())) {
                // 非子模型的情况下，校验关联模型的合法性
                objectRefField = object.getObjectRefField(ownerObjectName);
                if (objectRefField == null) {
                    throw new FastOqlException("模型" + ownerObjectName + "与当前模型" + objectName + "不存在关联关系，不允许访问");
                }
            }

            String propName = x.getName();
            if (Token.STAR.getName().equals(propName)) { // 展开整个模型
                // 识别为OQL模型展开表达式
                OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(owner);
                objectExpandExpr.setStarExpanded(true);
                objectExpandExpr.setResolvedObjectRefField(objectRefField);
                objectExpandExpr.setResolvedRefObject(ownerObject);
            } else {
                XField ownerField = ownerObject.getField(propName);
                // 校验字段的存在性
                if (ownerField == null) {
                    throw new FastOqlException("模型" + ownerObjectName + "中的字段" + propName + "不存在！");
                }

                // 识别为OQL字段表达式
                OqlFieldExpr fieldExpr = new OqlFieldExpr(new SqlIdentifierExpr(ownerObjectName), propName);
                fieldExpr.setResolvedField(ownerField);
                sqlX = fieldExpr;
            }
        }

        return sqlX;
    }

    /**
     * 解析 Like 表达式
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseLikeExpr(XObject object, SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = new SqlLikeOpExpr();
        sqlX.setLeft(this.parseSqlExpr(object, x.getLeft()));
        sqlX.setRight(this.parseSqlExpr(object, x.getRight()));
        return sqlX;
    }

    /**
     * 解析 InList 表达式
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseInListExpr(XObject object, SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setLeft(this.parseSqlExpr(object, x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.parseSqlExpr(object, targetItem));
        }
        return sqlX;
    }

    /**
     * 解析二元操作表达式
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExpr(XObject object, SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = new SqlBinaryOpExpr();
        sqlX.setLeft(this.parseSqlExpr(object, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.parseSqlExpr(object, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    /**
     * 解析二元操作表达式组合
     *
     * @param object
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExprGroup(XObject object, SqlBinaryOpExprGroup x) {
        SqlBinaryOpExprGroup sqlX = new SqlBinaryOpExprGroup(x.getOperator());
        List<SqlExpr> items = x.getItems();
        for (SqlExpr item : items) {
            sqlX.addItem(this.parseSqlExpr(object, item));
        }

        return sqlX;
    }

    /**
     * 解析聚合表达式
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseAggregateExpr(XObject object, SqlAggregateExpr x) {
        SqlAggregateExpr sqlX = new SqlAggregateExpr();
        sqlX.setMethodName(x.getMethodName());
        List<SqlExpr> args = x.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAllColumnExpr) {
                sqlX.addArgument(arg);
            } else {
                sqlX.addArgument(this.parseSqlExpr(object, arg));
            }
        }
        return sqlX;
    }

    /**
     * 解析方法调用表达式
     *
     * @param object 当前模型
     * @param x
     * @return
     */
    private SqlExpr parseMethodInvokeExpr(XObject object, SqlMethodInvokeExpr x) {
        SqlExpr sqlX = x;
        String methodName = x.getMethodName();
        if (this.supportedMethodNames.contains(methodName)) {
            return sqlX;
        }

        XObject refObject = this.resolver.resolve(methodName);
        if (refObject != null) { // 模型展开
            sqlX = this.expandObject(object, refObject, x);
        } else { // 字段展开
            sqlX = this.expandObjectField(object, x);
        }

        return sqlX;
    }

    /**
     * 展开模型
     *
     * @param object    当前模型
     * @param refObject 展开的关联模型
     * @param x
     */
    private OqlObjectExpandExpr expandObject(XObject object, XObject refObject, SqlMethodInvokeExpr x) {
        String refObjectName = refObject.getName();
        XObjectRefField objectRefField = null;
        if (!object.getName().equals(refObjectName)) {
            objectRefField = object.getObjectRefField(refObjectName);
            if (objectRefField == null) {
                throw new FastOqlException("模型" + refObjectName + "与当前模型不存在关联关系");
            }
        }

        OqlObjectExpandExpr objectExpandExpr = new OqlObjectExpandExpr(refObjectName);
        objectExpandExpr.setResolvedObjectRefField(objectRefField);
        objectExpandExpr.setResolvedRefObject(refObject);
        List<SqlExpr> args = x.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAggregateExpr) {
                throw new FastOqlException("模型展开的字段中不能有嵌合函数");
            } else if (arg instanceof SqlMethodInvokeExpr) { // 内嵌展开字段
                SqlExpr argX = this.parseMethodInvokeExpr(refObject, (SqlMethodInvokeExpr) arg);
                objectExpandExpr.addField(argX);
            } else if (arg instanceof SqlIdentifierExpr) {
                String fieldName = ((SqlIdentifierExpr) arg).getName();
                XField field = refObject.getField(fieldName);
                // 校验字段的存在性
                if (field == null) {
                    throw new FastOqlException("模型" + refObjectName + "中的属性" + fieldName + "不存在");
                }

                // 解析为一个属性
                List<XProperty> properties = field.getProperties();
                if (properties != null && properties.size() > 0) {
                    OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(fieldName);
                    fieldExpandExpr.setDefaultExpanded(true);
                    fieldExpandExpr.setResolvedField(field);
                    objectExpandExpr.addField(fieldExpandExpr);
                } else {
                    OqlFieldExpr fieldExpr = new OqlFieldExpr(null, fieldName);
                    fieldExpr.setResolvedField(field);
                    objectExpandExpr.addField(fieldExpr);
                }
            } else {
                objectExpandExpr.addField(this.parseSqlExpr(refObject, arg));
            }
        }

        return objectExpandExpr;
    }

    /**
     * 展开模型字段
     *
     * @param object
     * @param x
     * @return
     */
    private OqlFieldExpandExpr expandObjectField(XObject object, SqlMethodInvokeExpr x) {
        String methodName = x.getMethodName();
        XField field = object.getField(methodName);
        if (field == null) {
            throw new FastOqlException("模型" + object.getName() + "中的字段" + methodName + "不存在");
        }

        String fieldName = field.getName();
        OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(methodName);
        fieldExpandExpr.setResolvedField(field);
        List<SqlExpr> args = x.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAggregateExpr) {
                throw new FastOqlException("模型展开的字段中不能有嵌合函数");
            } else if (arg instanceof SqlMethodInvokeExpr) {
                throw new FastOqlException("模型展开的字段中不能内嵌展开");
            } else if (arg instanceof SqlIdentifierExpr) {
                String propName = ((SqlIdentifierExpr) arg).getName();
                XProperty property = field.getProperty(propName);
                // 校验属性的存在性
                if (property == null) {
                    throw new FastOqlException("字段" + fieldName + "中的属性" + propName + "不存在");
                }

                // 解析为一个属性
                OqlPropertyExpr propExpr = new OqlPropertyExpr(null, propName);
                propExpr.setResolvedProperty(property);
                fieldExpandExpr.addProperty(propExpr);
            } else {
                fieldExpandExpr.addProperty(this.parseSqlExpr(object, arg));
            }
        }

        return fieldExpandExpr;
    }

}
