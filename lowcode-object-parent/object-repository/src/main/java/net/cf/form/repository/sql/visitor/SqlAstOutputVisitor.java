package net.cf.form.repository.sql.visitor;

import net.cf.form.repository.sql.ast.SqlCommentHint;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.Token;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * SQL语句输出访问器
 *
 * @author clouds
 */
public class SqlAstOutputVisitor extends SqlAstVisitorAdaptor implements ParameterizedVisitor, PrintableVisitor {


    /**
     * 列表中每项输出的分隔符
     */
    private static final String LIST_ITEM_SEPARATOR = ", ";

    /**
     * 输出目标
     */
    protected final Appendable appender;

    /**
     * 是否大写输出
     */
    protected boolean uppercase = false;

    /**
     * 是否参数化
     */
    protected boolean parameterized;

    /**
     * 参数列表
     */
    protected List<Object> parameters;

    /**
     * 参数替换的数量
     */
    protected int replaceCount;

    /**
     * 标识符的引用符号
     */
    protected char quote = '`';

    /**
     * 是否打印标识符的引用符号
     */
    protected boolean printNameQuote;

    /**
     * 输出总行数
     */
    protected transient int lines;

    /**
     * 缩进的次数
     */
    protected int indentCount;


    public SqlAstOutputVisitor(Appendable appender) {
        this.features |= VisitorFeature.OUTPUT_PRETTY_FORMAT.mask;
        this.appender = appender;
    }

    public SqlAstOutputVisitor(Appendable appender, boolean parameterized) {
        this.features |= VisitorFeature.OUTPUT_PRETTY_FORMAT.mask;
        this.appender = appender;
        this.config(VisitorFeature.OUTPUT_PARAMETERIZED, parameterized);
    }

    @Override
    public int getReplaceCount() {
        return this.replaceCount;
    }

    @Override
    public void incrementReplaceCount() {
        this.replaceCount++;
    }

    @Override
    public void setOutputParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void config(VisitorFeature feature, boolean state) {
        super.config(feature, state);
        if (feature == VisitorFeature.OUTPUT_U_CASE) {
            this.uppercase = state;
        } else if (feature == VisitorFeature.OUTPUT_PARAMETERIZED) {
            this.parameterized = state;
        } else if (feature == VisitorFeature.OUTPUT_NAME_QUOTE) {
            this.printNameQuote = state;
        }
    }


    /**
     * 配置迭代器的特性
     *
     * @param features
     */
    public void setFeatures(int features) {
        super.setFeatures(features);
        this.uppercase = this.isEnabled(VisitorFeature.OUTPUT_U_CASE);
        this.parameterized = this.isEnabled(VisitorFeature.OUTPUT_PARAMETERIZED);
        this.printNameQuote = this.isEnabled(VisitorFeature.OUTPUT_NAME_QUOTE);
    }

    /**
     * 是否是格式化后的格式
     *
     * @return
     */
    public boolean isPrettyFormat() {
        return this.isEnabled(VisitorFeature.OUTPUT_PRETTY_FORMAT);
    }

    public char getNameQuote() {
        return this.quote;
    }

    public void setNameQuote(char quote) {
        this.quote = quote;
    }


    @Override
    public boolean isUppercase() {
        return this.uppercase;
    }

    @Override
    public void print(String text) {
        if (this.appender != null) {
            try {
                this.appender.append(text);
            } catch (IOException e) {
                throw new RuntimeException("println error", e);
            }
        }
    }

    @Override
    public void print(char value) {
        if (this.appender != null) {
            try {
                this.appender.append(value);
            } catch (IOException e) {
                throw new RuntimeException("print error", e);
            }
        }
    }

    /**
     * 打印一个换行符
     */
    public void println() {
        if (!this.isPrettyFormat()) {
            this.print(' ');
        } else {
            this.print('\n');
            ++this.lines;
            this.printIndent();
        }
    }

    /**
     * 打印缩进符
     */
    protected void printIndent() {
        if (this.appender != null) {
            try {
                for (int i = 0; i < this.indentCount; ++i) {
                    this.appender.append('\t');
                }
            } catch (IOException e) {
                throw new RuntimeException("print error", e);
            }
        }
    }

    /**
     * 打印字符串
     *
     * @param text
     */
    protected void printChars(final String text) {
        String tText = text;
        if (tText == null) {
            this.print(this.uppercase ? "NULL" : "null");
        } else {
            this.print('\'');
            int index = tText.indexOf("'");
            if (index >= 0) {
                tText = tText.replaceAll("'", "''");
            }

            this.print(tText);
            this.print('\'');
        }
    }

    /**********************************************************************
     *                基础类节点                                            *
     **********************************************************************/

    @Override
    public boolean visit(SqlCommentHint x) {
        this.print("/*");
        this.print(x.getText());
        this.print("*/");

        return false;
    }

    /**********************************************************************
     *                字面量类表达式节点                                      *
     **********************************************************************/

    @Override
    public boolean visit(SqlCharExpr x) {
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlBooleanExpr x) {
        if (this.uppercase) {
            this.print(x.getValue() ? "TRUE" : "FALSE");
        } else {
            this.print(x.getValue() ? "true" : "false");
        }

        return false;
    }

    @Override
    public boolean visit(SqlIntegerExpr x) {
        this.print(x.getValue().toString());
        return false;
    }

    @Override
    public boolean visit(SqlDecimalExpr x) {
        this.print(x.getNumber().toString());
        return false;
    }

    @Override
    public boolean visit(SqlTimestampExpr x) {
        this.print(this.uppercase ? "DATETIME " : "datetime ");
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlDateExpr x) {
        this.print(this.uppercase ? "DATE " : "date ");
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlNullExpr x) {
        this.print(this.uppercase ? "NULL" : "null");
        return false;
    }

    @Override
    public boolean visit(SqlListExpr x) {
        this.printParenthesesAndAcceptList(x.getItems(), ", ");
        return false;
    }

    @Override
    public boolean visit(SqlJsonObjectExpr x) {
        this.print("{");
        Map<String, SqlValuableExpr> items = x.getItems();
        int i = 0;
        for (Map.Entry<String, SqlValuableExpr> entry : items.entrySet()) {
            if (i++ > 0) {
                this.print(", ");
            }
            this.print("\"" + entry.getKey() + "\": ");
            SqlExpr value = entry.getValue();
            if (value instanceof SqlCharExpr) {
                this.print("\"" + ((SqlCharExpr) value).getValue() + "\"");
            } else {
                value.accept(this);
            }
        }
        this.print("}");
        return false;
    }

    @Override
    public boolean visit(SqlJsonArrayExpr x) {
        this.print("[");
        List<SqlExpr> items = x.getItems();
        for (int i = 0, l = items.size(); i < l; i++) {
            if (i != 0) {
                this.print(LIST_ITEM_SEPARATOR);
            }
            SqlExpr item = items.get(i);
            if (item instanceof SqlCharExpr) {
                this.print("\"" + ((SqlCharExpr) item).getValue() + "\"");
            } else {
                item.accept(this);
            }
        }
        this.print("]");
        return false;
    }

    /**********************************************************************
     *                操作类表达式节点                                         *
     **********************************************************************/

    @Override
    public boolean visit(SqlBinaryOpExprGroup x) {
        String op = x.getOperator().name;
        String separator = this.uppercase ? op.toUpperCase() : op.toUpperCase();
        this.printParenthesesAndAcceptList(x.getChildren(), " " + separator + " ");
        return false;
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        if (x.isParenthesized()) {
            this.print("(");
        }

        x.getLeft().accept(this);
        this.print(' ');
        String op = x.getOperator().name;
        this.print(this.uppercase ? op.toUpperCase() : op.toUpperCase());
        this.print(' ');
        x.getRight().accept(this);

        if (x.isParenthesized()) {
            this.print(")");
        }

        return false;
    }

    @Override
    public boolean visit(SqlLikeOpExpr x) {
        x.getLeft().accept(this);
        if (x.isNot()) {
            this.print(this.uppercase ? " NOT " : " not ");
        }
        this.print(this.uppercase ? " LIKE " : " like ");
        x.getRight().accept(this);

        String escape = x.getEscape();
        if (escape != null) {
            this.print(this.uppercase ? " ESCAPE " : " escape ");
            this.printChars(escape);
        }

        return false;
    }

    @Override
    public boolean visit(SqlInListExpr x) {
        x.getLeft().accept(this);
        if (x.isNot()) {
            this.print(this.uppercase ? " NOT " : " not ");
        }
        this.print(this.uppercase ? " IN " : " in ");
        this.printParenthesesAndAcceptList(x.getTargetList(), LIST_ITEM_SEPARATOR);
        return false;
    }

    @Override
    public boolean visit(SqlExistsExpr x) {
        if (x.isNot()) {
            this.print(this.uppercase ? "NOT " : "not ");
        }
        this.print(this.uppercase ? "EXISTS (" : "exists (");
        this.visit(x.getSubQuery());
        this.print(")");

        return false;
    }

    @Override
    public boolean visit(SqlContainsOpExpr x) {
        x.getLeft().accept(this);
        if (x.isNot()) {
            this.print(this.uppercase ? "NOT " : "not ");
        }
        this.print(this.uppercase ? " CONTAINS " : " contains ");
        x.getRight().accept(this);
        return false;
    }

    /**********************************************************************
     *                标识符类表达式节点                                         *
     **********************************************************************/

    @Override
    public boolean visit(SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        if (owner != null) {
            owner.accept(this);
            this.print('.');
        }
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        this.print(x.getName());
        return false;
    }

    @Override
    public boolean visit(SqlVariantRefExpr x) {
        this.print(x.getName());
        List<String> subVarNames = x.getSubVarNames();
        if (!CollectionUtils.isEmpty(subVarNames)) {
            this.print("(");
            int l = subVarNames.size();
            for (int i = 0; i < l; i++) {
                if (i > 0) {
                    this.print(", ");
                }
                this.print(subVarNames.get(i));
            }
        }

        return false;
    }

    @Override
    public boolean visit(SqlMethodInvokeExpr x) {
        String methodName = x.getMethodName();
        this.print(this.uppercase ? methodName.toUpperCase() : methodName.toLowerCase());
        this.printParenthesesAndAcceptList(x.getArguments(), LIST_ITEM_SEPARATOR);
        return false;
    }

    @Override
    public boolean visit(SqlAggregateExpr x) {
        String methodName = x.getMethodName();
        this.print(this.uppercase ? methodName.toUpperCase() : methodName.toLowerCase());
        this.printParenthesesAndAcceptList(x.getArguments(), LIST_ITEM_SEPARATOR);
        return false;
    }

    @Override
    public boolean visit(SqlAllColumnExpr x) {
        this.print(Token.STAR.name);
        return false;
    }

    /**********************************************************************
     *                表源类节点                                            *
     **********************************************************************/
    @Override
    public boolean visit(SqlExprTableSource x) {
        x.getExpr().accept(this);
        String alias = x.getAlias();
        if (alias != null) {
            this.print(this.uppercase ? " AS " : " as ");
            this.print(alias);
        }

        return false;
    }

    @Override
    public boolean visit(SqlSubQueryTableSource x) {
        return super.visit(x);
    }

    /**********************************************************************
     *                语句类节点                                            *
     **********************************************************************/

    @Override
    public boolean visit(SqlSelect x) {
        if (x.isParenthesized()) {
            this.print("(");
        }

        this.print(this.uppercase ? "SELECT " : "select ");

        DistinctOption distinctOption = x.getDistinctOption();
        if (distinctOption != null) {
            if (DistinctOption.ALL == distinctOption) {
                this.print(this.uppercase ? "ALL " : "all ");
            } else if (DistinctOption.DISTINCT == distinctOption) {
                this.print(this.uppercase ? "DISTINCT " : "distinct ");
            } else if (DistinctOption.UNIQUE == distinctOption) {
                this.print(this.uppercase ? "UNIQUE " : "unique ");
            }
        }

        // 输出查询的列
        List<SqlSelectItem> selectItems = x.getSelectItems();
        this.printAndAcceptList(selectItems, LIST_ITEM_SEPARATOR);

        // 输出查询的表
        SqlTableSource from = x.getFrom();
        if (from != null) {
            this.println();
            this.print(this.uppercase ? "FROM " : "from ");
            from.accept(this);
        }

        // 输出where条件
        this.printWhere(x.getWhere());

        // 输出groupBy子句
        SqlSelectGroupByClause groupBy = x.getGroupBy();
        if (groupBy != null) {
            this.println();
            groupBy.accept(this);
        }

        // 输出orderBy
        SqlOrderBy orderBy = x.getOrderBy();
        if (orderBy != null) {
            this.println();
            orderBy.accept(this);
        }

        SqlLimit limit = x.getLimit();
        if (limit != null) {
            limit.accept(this);
        }

        if (x.isParenthesized()) {
            this.print(")");
        }

        return false;
    }

    @Override
    public boolean visit(SqlSelectItem x) {
        x.getExpr().accept(this);
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }

    @Override
    public boolean visit(SqlCaseExpr x) {
        ++this.indentCount;
        this.print(this.uppercase ? "CASE " : "case ");
        SqlExpr valueExpr = x.getValueExpr();
        if (valueExpr != null) {
            valueExpr.accept(this);
        }

        List<SqlCaseExpr.Item> items = x.getItems();
        for (int i = 0, size = items.size(); i < size; ++i) {
            this.println();
            this.visit(items.get(i));
        }

        SqlExpr elExpr = x.getElseExpr();
        if (elExpr != null) {
            this.println();
            this.print(this.uppercase ? "ELSE " : "else ");
            if (elExpr instanceof SqlCaseExpr) {
                ++this.indentCount;
                this.println();
                this.visit((SqlCaseExpr) elExpr);
                --this.indentCount;
            } else {
                elExpr.accept(this);
            }
        }

        --this.indentCount;
        this.println();
        this.print(this.uppercase ? "END" : "end");
        return false;
    }

    @Override
    public boolean visit(SqlCaseExpr.Item x) {
        this.print(this.uppercase ? "WHEN " : "when ");
        SqlExpr conditionExpr = x.getConditionExpr();
        ++this.indentCount;
        conditionExpr.accept(this);
        --this.indentCount;
        this.print(' ');

        this.print(this.uppercase ? "THEN " : "then ");
        SqlExpr valueExpr = x.getValueExpr();
        if (valueExpr instanceof SqlCaseExpr) {
            ++this.indentCount;
            this.println();
            this.visit((SqlCaseExpr) valueExpr);
            --this.indentCount;
        } else {
            ++this.indentCount;
            valueExpr.accept(this);
            --this.indentCount;
        }

        return false;
    }

    @Override
    public boolean visit(SqlSelectGroupByClause x) {
        this.print(this.uppercase ? "GROUP BY " : "group by ");
        this.printAndAcceptList(x.getItems(), ", ");
        return false;
    }

    @Override
    public boolean visit(SqlOrderBy x) {
        this.print(this.uppercase ? "ORDER BY " : "order by ");
        return true;
    }

    @Override
    public boolean visit(SqlSelectOrderByItem x) {
        x.getExpr().accept(this);
        if (x.isAscending()) {
            this.print(this.uppercase ? " ASC" : " asc");
        } else {
            this.print(this.uppercase ? " DESC" : " desc");
        }
        return false;
    }

    @Override
    public boolean visit(SqlLimit x) {
        this.print(this.uppercase ? " LIMIT " : " limit ");
        SqlExpr offset = x.getOffset();
        if (offset != null) {
            offset.accept(this);
            this.print(LIST_ITEM_SEPARATOR);
        }

        SqlExpr rowCount = x.getRowCount();
        if (rowCount != null) {
            rowCount.accept(this);
        }

        return false;
    }

    @Override
    public boolean visit(SqlInsertStatement x) {
        this.print(this.uppercase ? "INSERT INTO " : "insert into ");

        // 输出表源
        x.getTableSource().accept(this);

        // 输出插入的列
        this.printParenthesesAndAcceptList(x.getColumns(), LIST_ITEM_SEPARATOR);

        // 输出插入的列列表
        List<SqlInsertStatement.ValuesClause> valuesClauses = x.getValuesList();
        if (!valuesClauses.isEmpty()) {
            this.println();
            this.print(this.uppercase ? "VALUES " : "values ");
            this.printAndAcceptList(valuesClauses, LIST_ITEM_SEPARATOR);
        }

        return false;
    }

    @Override
    public boolean visit(SqlInsertStatement.ValuesClause x) {
        this.printParenthesesAndAcceptList(x.getValues(), LIST_ITEM_SEPARATOR);
        return false;
    }

    @Override
    public boolean visit(SqlUpdateStatement x) {
        this.print(this.uppercase ? "UPDATE " : "update ");

        // 输出表源
        x.getTableSource().accept(this);

        // 输出set子句
        this.print(this.uppercase ? " SET " : " set ");
        this.printAndAcceptList(x.getSetItems(), LIST_ITEM_SEPARATOR);

        // 输出where条件
        this.printWhere(x.getWhere());

        return false;
    }

    @Override
    public boolean visit(SqlUpdateSetItem x) {
        x.getColumn().accept(this);
        this.print(" = ");
        x.getValue().accept(this);
        return false;
    }

    @Override
    public boolean visit(SqlDeleteStatement x) {
        this.print(this.uppercase ? "DELETE FROM " : "delete from ");

        // 输出表源
        x.getFrom().accept(this);

        // 输出where条件
        this.printWhere(x.getWhere());

        return false;
    }

    /**********************************************************************
     *                        辅助函数                                      *
     **********************************************************************/

    /**
     * 打印小括号括号来的，并以某个分隔符分开的列表
     *
     * @param nodes
     * @param separator
     */
    protected void printParenthesesAndAcceptList(List<? extends SqlObject> nodes, String separator) {
        this.print('(');
        this.printAndAcceptList(nodes, separator);
        this.print(')');
    }


    /**
     * 打印以某个分隔符分开的列表
     *
     * @param nodes
     * @param separator
     */
    protected void printAndAcceptList(List<? extends SqlObject> nodes, String separator) {
        int l = nodes.size();
        for (int i = 0; i < l; i++) {
            if (i != 0) {
                this.print(separator);
            }
            nodes.get(i).accept(this);
        }
    }

    /**
     * 输出where条件
     *
     * @param where
     */
    protected void printWhere(SqlExpr where) {
        if (where != null) {
            this.println();
            this.print(this.uppercase ? "WHERE " : "where ");
            where.accept(this);
        }
    }
}
