package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.util.SqlExprUtils;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.infos.OqlSelectInfosParser;
import net.cf.object.engine.util.OqlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL 语句中的 where 子句构建器
 * <p>
 * 职责：将select、update、delete OQL语句的where子句构建成 SQL 的 where 子句
 *
 * <p>
 * 约束：
 * 1、不允许出现和本模型无关的模型
 * 2、分组最多嵌套一层，如允许：a = 1 or b = c 或者 (a = 1 and b = 2) or (c = 1 and d = 3)，不允许：(a = 1 and b = 2 and (c = d and d = 3)) or (c = 1 and d = 3)
 * 3、二元操作符的左值和右值对应的模型必须是同一个，如这样不允许：refField.b = a，refField.b是引用表的字段，a是本表的字段
 * 优化：
 * 1、同一个最底层分组里的标识符，属于同一个模型的表达式组合在一起, a = 1 and b = 2 and refField.a = 1 and refField.b = 2, a = 1 and b = 2 and (refField.a = 1 or refField.b = 2)
 * 2、对于非本表的二元操作表达式，转换为exists子查询
 *
 * @author clouds
 */
public class SqlWhereBuilder extends AbstractSqlBuilder {

    // 本模型
    private XObject selfObject;

    // 当前是否在分组中
    private boolean isInGroup = false;

    // 当前分组的层级
    private int currGroupLevel = 0;

    // 最后一次解析到的模型
    private XObject lastResolvedObject;

    /**
     * 主查询的模型，当构建子查询时，子查询有select 1 from t where t.id = m.id此类的需求
     */
    private XObject mainObject;

    /**
     * 主查询的模型的别名
     */
    private String mainObjectAlias;

    public SqlWhereBuilder(XObject selfObject) {
        this(selfObject, null, null);
    }


    public SqlWhereBuilder(XObject selfObject, XObject mainObject, String mainObjectAlias) {
        this.selfObject = selfObject;
        this.lastResolvedObject = selfObject;
        this.mainObject = mainObject;
        this.mainObjectAlias = mainObjectAlias;
    }

    /**
     * Where子句解析
     * <p>
     * 解析过程会过语法进行校验，并将语法转换为驱动层的语法，如：字段名转驱动的列名、引用表的查询条件转为exists子查询等
     *
     * @param x where条件
     */
    public SqlExpr parseExpr(SqlExpr x) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(x instanceof SqlIdentifierExpr) && !(x instanceof SqlPropertyExpr));

        Class<?> clazz = x.getClass();
        if (clazz == OqlFieldExpr.class) {
            return this.parseFieldExpr((OqlFieldExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            return this.parsePropertyExpr((OqlPropertyExpr) x);
        } else if (clazz == OqlObjectExpandExpr.class) {
            throw new FastOqlException("模型展开字段不能作为查询条件");
        } else if (clazz == OqlExistsExpr.class) {
            return this.parseExistsExpr((OqlExistsExpr) x, this.mainObject, this.mainObjectAlias);
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.parseLikeExpr((SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.parseInListExpr((SqlInListExpr) x);
        } else if (clazz == SqlContainsOpExpr.class) {
            return this.parseContainsOpExpr((SqlContainsOpExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.parseBinaryOpExpr((SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.parseBinaryOpExprGroup((SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.parseAggregateExpr((SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.parseMethodInvokeExpr((SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 解析标识符表达式
     *
     * @param expr
     * @return
     */
    private SqlExpr parseFieldExpr(OqlFieldExpr expr) {
        String fieldName = expr.getName();
        XField resolvedField = expr.getResolvedField();
        this.lastResolvedObject = resolvedField.getOwner();

        // 如果字段下存在子属性的话，那么转换为字段展开表达式（默认展开）
        List<XProperty> properties = resolvedField.getProperties();
        if (properties != null && properties.size() > 0) {
            if (properties.size() == 1) {
                return this.buildSqlExpr(properties.get(0));
            } else if (resolvedField.getPrimaryProperty() != null) {
                return this.buildSqlExpr(resolvedField.getPrimaryProperty());
            } else {
                throw new FastOqlException("查询条件的字段" + fieldName + "存在多个子属性、并且未设置主属性，请明确指明字段的属性");
            }
        } else {
            String owner = expr.getOwner();
            if (resolvedField.getOwner() != this.selfObject) {
                if (owner == null) {
                    return this.buildSqlRefExpr(resolvedField);
                } else {
                    return this.buildSqlExpr(resolvedField, owner);
                }
            } else {
                if (owner == null) {
                    return this.buildSqlExpr(resolvedField);
                } else {
                    return this.buildSqlExpr(resolvedField, owner);
                }
            }
        }
    }

    /**
     * 解析属性表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parsePropertyExpr(OqlPropertyExpr x) {
        XProperty resolvedProperty = x.getResolvedProperty();
        XField resolvedField = resolvedProperty.getOwner();
        this.lastResolvedObject = resolvedField.getOwner();
        return this.buildSqlExpr(resolvedProperty);
    }

    /**
     * 进入一个分组
     */
    private void enterGroup() {
        this.isInGroup = true;
        this.currGroupLevel++;
        if (currGroupLevel > 2) {
            throw new FastOqlException("不支持大于2级的查询条件表达式嵌套，如这个条件表达式已到达3级：where a = 1 and (b > 1 and (c < 1 and d > 1))");
        }
    }

    /**
     * 离开一个分组
     */
    private void leaveGroup() {
        this.isInGroup = false;
        this.currGroupLevel--;
    }

    /**
     * 解析 Like 表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseLikeExpr(SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = new SqlLikeOpExpr();
        sqlX.setNot(x.isNot());
        sqlX.setEscape(x.getEscape());
        return this.parseBinaryOpExprTo(x, sqlX);
    }

    /**
     * 解析 InList 表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseInListExpr(SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setNot(x.isNot());
        return this.parseBinaryOpExprTo(x, sqlX);
    }

    /**
     * 解析 Contains 表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseContainsOpExpr(SqlContainsOpExpr x) {
        SqlContainsOpExpr sqlX = new SqlContainsOpExpr();
        sqlX.setLeft(this.parseExpr(x.getLeft()));
        sqlX.setNot(x.isNot());
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.parseExpr(x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    /**
     * 解析二元操作表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExpr(SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = new SqlBinaryOpExpr();
        return this.parseBinaryOpExprTo(x, sqlX);
    }

    private SqlExpr parseBinaryOpExprTo(SqlBinaryOpExpr x, SqlBinaryOpExpr sqlX) {
        SqlBinaryOperator op = x.getOperator();
        SqlExpr leftExpr = x.getLeft();
        sqlX.setLeft(this.parseExpr(leftExpr));
        XObject leftObject = this.lastResolvedObject;
        sqlX.setOperator(op);
        SqlExpr rightExpr = x.getRight();
        sqlX.setRight(this.parseExpr(rightExpr));
        XObject rightObject = this.lastResolvedObject;
        sqlX.setParenthesized(x.isParenthesized());

        if (leftObject != rightObject && op != SqlBinaryOperator.BOOLEAN_AND && op != SqlBinaryOperator.BOOLEAN_OR && op != SqlBinaryOperator.BOOLEAN_XOR) {
            if (!(mainObject != null && (leftObject == mainObject || rightObject == mainObject))) { // 说明在子查询中，允许和主查询的字段比较
                throw new FastOqlException("非逻辑的二元操作表达式的左值与右值必须是必一个模型，请检查：" + x);
            }
        }

        // 如果mainObject不为null则本身已经是一个子查询了，不需要再转换一次了
        if (mainObject == null && !isInGroup && leftObject != selfObject) {
            String objectRefFieldName = null;
            if (leftExpr instanceof OqlFieldExpr) {
                objectRefFieldName = ((OqlFieldExpr) leftExpr).getOwner();
            } else if (rightExpr instanceof OqlFieldExpr) {
                objectRefFieldName = ((OqlFieldExpr) rightExpr).getOwner();
            }

            XObjectRefField objectRefField = selfObject.getObjectRefField2(objectRefFieldName);
            if (objectRefField == null) {
                throw new FastOqlException("二元操作的左值或右值是至少有一个模型引用字段");
            }

            return this.toExistsSubQueryExpr(objectRefField, leftObject, sqlX);
        } else {
            return sqlX;
        }
    }

    /**
     * 解析二元操作表达式组合
     *
     * @param x
     * @return
     */
    private SqlExpr parseBinaryOpExprGroup(SqlBinaryOpExprGroup x) {
        this.enterGroup();

        // 个分组中按模型映射的表达式
        Map<XObject, List<SqlExpr>> objectExprsInGroupMap = new HashMap<>();

        // 按模型聚合条件
        List<SqlExpr> items = x.getItems();
        for (SqlExpr item : items) {
            SqlExpr itemX = this.parseExpr(item);
            XObject currObject;
            if (itemX instanceof SqlExistsExpr) {
                currObject = this.selfObject;
            } else {
                currObject = this.lastResolvedObject;
            }
            List<SqlExpr> objectExprs = objectExprsInGroupMap.get(currObject);
            if (objectExprs == null) {
                objectExprs = new ArrayList<>();
                objectExprsInGroupMap.put(currObject, objectExprs);
            }
            objectExprs.add(itemX);
        }

        // 按模型生成条件
        List<SqlExpr> groupsX = new ArrayList<>();
        for (Map.Entry<XObject, List<SqlExpr>> entry : objectExprsInGroupMap.entrySet()) {
            XObject object = entry.getKey();
            List<SqlExpr> groupExprs = entry.getValue();
            SqlBinaryOpExprGroup groupX = new SqlBinaryOpExprGroup(x.getOperator());
            for (SqlExpr groupExpr : groupExprs) {
                groupX.addItem(groupExpr);
            }
            // 如果不是本表的查询条件，则转为exists子查询
            if (object != selfObject) {
                String objectRefFieldName = null;
                for (SqlExpr groupExpr : groupExprs) {
                    if (groupExpr instanceof OqlFieldExpr) {
                        objectRefFieldName = ((OqlFieldExpr) groupExpr).getOwner();
                        break;
                    }
                }

                XObjectRefField objectRefField = this.selfObject.getObjectRefField2(objectRefFieldName);
                if (objectRefField == null) {
                    throw new FastOqlException("二元操作的左值或右值是至少有一个模型引用字段");
                }
                groupsX.add(this.toExistsSubQueryExpr(objectRefField, object, groupX));
            } else {
                groupsX.add(groupX);
            }
        }

        this.leaveGroup();

        if (groupsX.size() == 1) {
            // 分完组后，只有一个条件时直接返回
            return groupsX.get(0);
        } else {
            // 分完组后，有多个条件时再形成一个分组表达式
            SqlBinaryOpExprGroup groupX = new SqlBinaryOpExprGroup(x.getOperator());
            for (SqlExpr group : groupsX) {
                groupX.addItem(group);
            }
            return groupX;
        }
    }

    /**
     * 将非本模型的查询条件转换为驱动层的exists子查询
     *
     * @param refObject 引用的模型
     * @param sqlX      引用模型的查询条件
     */
    private SqlExistsExpr toExistsSubQueryExpr(XObjectRefField objectRefField, XObject refObject, SqlExpr sqlX) {
        SqlExistsExpr existsExpr = new SqlExistsExpr();
        SqlSelect subQuery = new SqlSelect();
        subQuery.getSelectItems().add(new SqlSelectItem(new SqlIntegerExpr(1)));
        subQuery.setFrom(new SqlExprTableSource(refObject.getTableName()));
        ObjectRefType refType = objectRefField.getRefType();
        SqlBinaryOpExpr joinOpExpr = new SqlBinaryOpExpr();
        joinOpExpr.setOperator(SqlBinaryOperator.EQUALITY);
        // 设置关联条件
        if (refType == ObjectRefType.DETAIL) {
            // where detail.masterField = self.primaryField
            XObjectRefField detailMasterField = refObject.getMasterField();
            joinOpExpr.setLeft(this.buildSqlRefExpr(detailMasterField));
            XField selfPrimaryField = this.selfObject.getPrimaryField();
            joinOpExpr.setRight(this.buildSqlRefExpr(selfPrimaryField));
        } else if (refType == ObjectRefType.MASTER) {
            // where master.primaryField = self.masterField
            XField masterPrimaryField = refObject.getPrimaryField();
            joinOpExpr.setLeft(this.buildSqlRefExpr(masterPrimaryField));
            XObjectRefField selfMasterField = this.selfObject.getMasterField();
            joinOpExpr.setRight(this.buildSqlRefExpr(selfMasterField));
        } else if (refType == ObjectRefType.LOOKUP) {
            // where lookup.primaryField = self.lookupField
            XField lookupPrimaryField = refObject.getPrimaryField();
            joinOpExpr.setLeft(this.buildSqlRefExpr(lookupPrimaryField));
            //XObjectRefField selfLookupField = this.selfObject.getObjectRefField2(refObject.getName());
            joinOpExpr.setRight(this.buildSqlRefExpr(objectRefField));
        }
        subQuery.setWhere(SqlExprUtils.and(joinOpExpr, sqlX));
        existsExpr.setSubQuery(subQuery);

        return existsExpr;
    }

    /**
     * 解析聚合表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseAggregateExpr(SqlAggregateExpr x) {
        SqlAggregateExpr sqlX = new SqlAggregateExpr();
        this.cloneTo(x, sqlX);
        return sqlX;
    }

    /**
     * 解析方法调用表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseMethodInvokeExpr(SqlMethodInvokeExpr x) {
        String methodName = x.getMethodName();
        if (!OqlUtils.isValidMethodName(methodName)) {
            throw new FastOqlException("方法：" + methodName + "不支持！");
        }

        SqlMethodInvokeExpr sqlX = new SqlMethodInvokeExpr();
        this.cloneTo(x, sqlX);
        return sqlX;
    }

    /**
     * 解析exists表达式
     *
     * @param x
     * @return
     */
    private SqlExpr parseExistsExpr(OqlExistsExpr x, XObject mainObject, String mainObjectAlias) {
        OqlSelect subQuery = x.getSubQuery();

        OqlSelectStatement subQueryStmt = new OqlSelectStatement(subQuery);
        OqlSelectInfosParser subQueryParser = new OqlSelectInfosParser(subQueryStmt, mainObject, mainObjectAlias);
        SqlSelect sqlSubQuery = subQueryParser.parse().getMasterSelectCmd().getStatement().getSelect();

        SqlExistsExpr sqlX = new SqlExistsExpr();
        sqlX.setNot(x.isNot());
        sqlX.setSubQuery(sqlSubQuery);
        return sqlX;
    }

    /**
     * 拷贝一个方法调用表达式到目标方法调用表达式
     *
     * @param sqlX
     * @param sqlY
     */
    private void cloneTo(SqlMethodInvokeExpr sqlX, SqlMethodInvokeExpr sqlY) {
        sqlY.setMethodName(sqlX.getMethodName());
        List<SqlExpr> args = sqlX.getArguments();
        for (SqlExpr arg : args) {
            if (arg instanceof SqlAllColumnExpr) {
                sqlY.addArgument(arg);
            } else {
                sqlY.addArgument(this.parseExpr(arg));
            }
        }
    }

}
