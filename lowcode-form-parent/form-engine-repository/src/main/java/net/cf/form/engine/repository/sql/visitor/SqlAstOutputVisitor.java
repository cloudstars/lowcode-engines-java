package net.cf.form.engine.repository.sql.visitor;

import net.cf.form.engine.repository.sql.ast.SqlCommentHint;
import net.cf.form.engine.repository.sql.ast.SqlObject;
import net.cf.form.engine.repository.sql.ast.expr.SqlCaseExpr;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.ast.expr.SqlListExpr;
import net.cf.form.engine.repository.sql.ast.expr.identifier.*;
import net.cf.form.engine.repository.sql.ast.expr.literal.*;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlBinaryOpExprGroup;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlNotExpr;
import net.cf.form.engine.repository.sql.ast.statement.*;
import net.cf.form.engine.repository.sql.parser.Token;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 输出访问器
 *
 * @author clouds
 */
public class SqlAstOutputVisitor extends SqlAstVisitorAdaptor implements ParameterizedVisitor, PrintableVisitor {

    public static Boolean defaultPrintStatementAfterSemi;

    protected Boolean printStatementAfterSemi;

    protected final Appendable appender;

    protected int replaceCount;

    protected transient int lines;

    protected int indentCount;

    protected List<Object> parameters;

    protected boolean uppercase = true;

    protected boolean parameterized;

    protected char quote = '`';

    protected boolean printNameQuote;


    public SqlAstOutputVisitor(Appendable appender) {
        this.printStatementAfterSemi = SqlAstOutputVisitor.defaultPrintStatementAfterSemi;
        this.features |= VisitorFeature.OutputPrettyFormat.mask;
        this.appender = appender;
    }

    public SqlAstOutputVisitor(Appendable appender, boolean parameterized) {
        this.printStatementAfterSemi = SqlAstOutputVisitor.defaultPrintStatementAfterSemi;
        this.features |= VisitorFeature.OutputPrettyFormat.mask;
        this.appender = appender;
        this.config(VisitorFeature.OutputParameterized, parameterized);
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
    public boolean isUpperCase() {
        return this.uppercase;
    }


    public void print(char value) {
        if (this.appender != null) {
            try {
                this.appender.append(value);
            } catch (IOException e) {
                throw new RuntimeException("print error", e);
            }
        }
    }

    public void print(int value) {
        if (this.appender != null) {
            if (this.appender instanceof StringBuffer) {
                ((StringBuffer) this.appender).append(value);
            } else if (this.appender instanceof StringBuilder) {
                ((StringBuilder) this.appender).append(value);
            } else {
                this.print(Integer.toString(value));
            }
        }
    }

    public void print(long value) {
        if (this.appender != null) {
            if (this.appender instanceof StringBuilder) {
                ((StringBuilder) this.appender).append(value);
            } else if (this.appender instanceof StringBuffer) {
                ((StringBuffer) this.appender).append(value);
            } else {
                this.print(Long.toString(value));
            }

        }
    }

    public void print(float value) {
        if (this.appender != null) {
            if (this.appender instanceof StringBuilder) {
                ((StringBuilder) this.appender).append(value);
            } else if (this.appender instanceof StringBuffer) {
                ((StringBuffer) this.appender).append(value);
            } else {
                this.print(Float.toString(value));
            }

        }
    }

    public void print(double value) {
        if (this.appender != null) {
            if (this.appender instanceof StringBuilder) {
                ((StringBuilder) this.appender).append(value);
            } else if (this.appender instanceof StringBuffer) {
                ((StringBuffer) this.appender).append(value);
            } else {
                this.print(Double.toString(value));
            }

        }
    }

    public void print(Date date) {
        if (this.appender != null) {
            SimpleDateFormat dateFormat;
            if (date instanceof java.sql.Date) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                this.print("DATE ");
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                this.print("TIMESTAMP ");
            }

            this.print("'" + dateFormat.format(date) + "'");
        }
    }

    public void println() {
        if (!this.isPrettyFormat()) {
            this.print(' ');
        } else {
            this.print('\n');
            ++this.lines;
            this.printIndent();
        }
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

    protected void printName(String text) {
        if (this.appender != null && text.length() != 0) {
            try {
                if (this.printNameQuote) {
                    char c0 = text.charAt(0);
                    if (c0 == this.quote) {
                        this.appender.append(text);
                    } else if (c0 == '"' && text.charAt(text.length() - 1) == '"') {
                        this.appender.append(this.quote);
                        this.appender.append(text.substring(1, text.length() - 1));
                        this.appender.append(this.quote);
                    } else if (c0 == '`' && text.charAt(text.length() - 1) == '`') {
                        this.appender.append(this.quote);
                        this.appender.append(text.substring(1, text.length() - 1));
                        this.appender.append(this.quote);
                    } else {
                        this.appender.append(this.quote);
                        this.appender.append(text);
                        this.appender.append(this.quote);
                    }
                } else {
                    this.appender.append(text);
                }

            } catch (IOException var3) {
                throw new RuntimeException("println error", var3);
            }
        }
    }

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


    protected void parenthesizedPrintAndAccept(List<? extends SqlObject> nodes, String separator) {
        this.print('(');
        this.printAndAccept(nodes, separator);
        this.print(')');
    }


    protected void printAndAccept(List<? extends SqlObject> nodes, String seperator) {
        for (int i = 0, l = nodes.size(); i < l; ++i) {
            if (i != 0) {
                this.print(seperator);
            }
            nodes.get(i).accept(this);
        }
    }

    @Override
    public boolean visit(SqlCommentHint x) {
        this.print("/*");
        this.print(x.getText());
        this.print("*/");

        return false;
    }

    protected final void printExpr(SqlExpr x) {
        this.printExpr(x, this.parameterized);
    }


    protected final void printExpr(SqlExpr x, boolean parameterized) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlIdentifierExpr.class) {
            this.visit((SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            this.visit((SqlPropertyExpr) x);
        } else if (clazz == SqlAllColumnExpr.class) {
            this.print('*');
        } else if (clazz == SqlAggregateExpr.class) {
            this.visit((SqlAggregateExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            this.visit((SqlBinaryOpExpr) x);
        } else if (clazz == SqlCharExpr.class) {
            this.visit((SqlCharExpr) x, parameterized);
        } else if (clazz == SqlNullExpr.class) {
            this.visit((SqlNullExpr) x);
        } else if (clazz == SqlIntegerExpr.class) {
            this.printInteger((SqlIntegerExpr) x, parameterized);
        } else if (clazz == SqlNumberExpr.class) {
            this.visit((SqlNumberExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            this.visit((SqlMethodInvokeExpr) x);
        } else if (clazz == SqlVariantRefExpr.class) {
            this.visit((SqlVariantRefExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            this.visit((SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlCaseExpr.class) {
            this.visit((SqlCaseExpr) x);
        } else if (clazz == SqlListExpr.class) {
            this.visit((SqlListExpr) x);
        } else if (clazz == SqlNotExpr.class) {
            this.visit((SqlNotExpr) x);
        } else {
            x.accept(this);
        }
    }

    @Override
    public boolean visit(SqlNotExpr x) {
        this.print(this.uppercase ? "NOT " : "not ");
        this.printExpr(x.getExpr());

        return false;
    }

    @Override
    public boolean visit(SqlCharExpr x) {
        this.printChars(x.getText());

        return false;
    }


    public boolean visit(SqlCharExpr x, boolean parameterized) {
        if (parameterized) {
            this.print('?');
            /*
            this.incrementReplaceCunt();
            if (this.parameters != null) {
                ExportParameterVisitorUtils.exportParameter(this.parameters, x);
            }
            */

            return false;
        } else {
            this.printChars(x.getText());
            return false;
        }
    }


    protected void printChars(String text) {
        if (text == null) {
            this.print(this.uppercase ? "NULL" : "null");
        } else {
            this.print('\'');
            int index = text.indexOf(39);
            if (index >= 0) {
                text = text.replaceAll("'", "''");
            }

            this.print(text);
            this.print('\'');
        }

    }


    @Override
    public boolean visit(SqlBooleanExpr x) {
        this.printChars(x.getValue() ? "true" : "false");

        return false;
    }


    @Override
    public boolean visit(SqlIntegerExpr x) {
        boolean parameterized = this.parameterized;
        this.printInteger(x, parameterized);
        return false;
    }

    protected void printInteger(SqlIntegerExpr x, boolean parameterized) {
        Number number = x.getNumber();
        if (parameterized) {
            this.print('?');
            /*this.incrementReplaceCunt();
            if (this.parameters != null) {
                ExportParameterVisitorUtils.exportParameter(this.parameters, x);
            }*/
        } else {
            if (!(number instanceof BigDecimal) && !(number instanceof BigInteger)) {
                this.print(number.longValue());
            } else {
                this.print(number.toString());
            }

        }
    }

    @Override
    public boolean visit(SqlNumberExpr x) {
        this.print(x.getNumber().toString());

        return false;
    }

    @Override
    public boolean visit(SqlNullExpr x) {
        this.print("null");

        return false;
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        this.print(x.getSimpleName());

        return false;
    }

    @Override
    public boolean visit(SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        if (owner != null) {
            if (owner instanceof SqlIdentifierExpr) {
                visit((SqlIdentifierExpr) owner);
            } else if (owner instanceof SqlPropertyExpr) {
                visit((SqlPropertyExpr) owner);
            }
            this.print('.');
        }
        this.print(x.getSimpleName());

        return false;
    }

    @Override
    public boolean visit(SqlVariantRefExpr x) {
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        if (x.isParenthesized()) {
            this.print("(");
        }

        this.printExpr(x.getLeft());
        this.print(' ');
        this.print(x.getOperator().name);
        this.print(' ');
        this.printExpr(x.getRight());

        if (x.isParenthesized()) {
            this.print(")");
        }

        return false;
    }

    @Override
    public boolean visit(SqlMethodInvokeExpr x) {
        String function = x.getMethodName();
        if (function != null) {
            this.print(function);
        }

        List<SqlExpr> arguments = x.getArguments();
        this.parenthesizedPrintAndAccept(arguments, ", ");

        return false;
    }


    /**
     * 加小括号输出表达式
     *
     * @param x
     * @param function
     */
    protected void outputByParenthesized(SqlObject x, SqlObjectPrinterFunction function) {
        print("(");
        function.print(x);
        print(")");
    }


    public boolean visit(SqlInsertStatement x) {
        this.print(this.uppercase ? "INSERT INTO " : "insert into ");

        x.getTableSource().accept(this);
        //String columnsString = x.getColumnsString();
        //if (columnsString != null) {
        //    this.print0(columnsString);
        //} else {
        this.printInsertColumns(x.getColumns());
        //}

        if (!x.getValuesList().isEmpty()) {
            this.println();
            this.print(this.uppercase ? "VALUES " : "values ");
            this.printAndAccept(x.getValuesList(), ", ");
        } /*else if (x.getQuery() != null) {
            this.println();
            x.getQuery().accept(this);
        }*/

        return false;
    }


    public void printInsertColumns(List<SqlExpr> columns) {
        int size = columns.size();
        if (size > 0) {
            this.print(' ');
            this.print('(');

            for (int i = 0; i < size; ++i) {
                if (i != 0) {
                    this.print(", ");
                }

                SqlExpr column = columns.get(i);
                if (column instanceof SqlIdentifierExpr) {
                    this.visit((SqlIdentifierExpr) column);
                } else {
                    this.printExpr(column, this.parameterized);
                }
            }

            this.print(')');
        }
    }


    @Override
    public boolean visit(SqlInsertStatement.ValuesClause x) {
        this.print('(');
        this.printAndAccept(x.getValues(), ", ");
        this.print(')');

        return false;
    }


    @Override
    public boolean visit(SqlSelect x) {
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

        this.printSelectList(x.getSelectItems());

        SqlTableSource from = x.getFrom();
        if (from != null) {
            this.println();
            this.print(this.uppercase ? "FROM " : "from ");
            this.printTableSource(from);
        }

        SqlExpr where = x.getWhere();
        if (where != null) {
            this.printWhere(where);
        }

        SqlSelectGroupByClause groupBy = x.getGroupBy();
        if (groupBy != null) {
            this.println();
            this.visit(groupBy);
        }

        SqlOrderBy orderBy = x.getOrderBy();
        if (orderBy != null) {
            this.println();
            orderBy.accept(this);
        }

        return false;
    }

    protected void printSelectList(List<SqlSelectItem> selectList) {
        for (int i = 0, l = selectList.size(); i < l; i++) {
            SqlSelectItem selectItem = selectList.get(i);
            if (i != 0) {
                this.print(", ");
            }

            if (selectItem.getClass() == SqlSelectItem.class) {
                this.visit(selectItem);
            } else {
                selectItem.accept(this);
            }
        }
    }

    protected void printTableSource(SqlTableSource x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlJoinTableSource.class) {
            this.visit((SqlJoinTableSource) x);
        } else if (clazz == SqlExprTableSource.class) {
            this.visit((SqlExprTableSource) x);
        } else if (clazz == SqlSubqueryTableSource.class) {
            this.visit((SqlSubqueryTableSource) x);
        } else {
            x.accept(this);
        }
    }

    protected void printWhere(SqlExpr where) {
        this.println();
        this.print(this.uppercase ? "WHERE " : "where ");
        this.printExpr(where, this.parameterized);
    }

    @Override
    public boolean visit(SqlSelectItem x) {
        this.printExpr(x.getExpr());
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }

    @Override
    public boolean visit(SqlAllColumnExpr x) {
        this.print(Token.STAR.name);

        return false;
    }

    @Override
    public boolean visit(SqlExprTableSource x) {
        this.printTableSourceExpr(x.getExpr());

        String alias = x.getAlias();
        if (alias != null) {
            this.print(this.uppercase ? " AS " : " as ");
            this.print(alias);
        }

        return false;
    }


    @Override
    public boolean visit(SqlJoinTableSource x) {
        SqlTableSource leftTableSource = x.getLeft();
        this.printTableSource(leftTableSource);

        SqlJoinTableSource.JoinType joinType = x.getJoinType();
        if (joinType != null) {
            if (joinType != SqlJoinTableSource.JoinType.COMMA) {
                this.print(' ');
            }
            this.print(joinType.name);
            this.print(' ');
        }

        SqlTableSource rightTableSource = x.getRight();
        this.printTableSource(rightTableSource);

        if (x.getCondition() != null) {
            this.print(this.uppercase ? " ON " : " on ");
            this.printExpr(x.getCondition());
        }

        return false;
    }


    protected void printTableSourceExpr(SqlExpr expr) {
        if (expr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr identifierExpr = (SqlIdentifierExpr) expr;
            String destTableName = identifierExpr.getName();
            this.printName(destTableName);
        } else if (expr instanceof SqlPropertyExpr) {
            SqlPropertyExpr propertyExpr = (SqlPropertyExpr) expr;
            SqlExpr owner = propertyExpr.getOwner();
            if (owner instanceof SqlIdentifierExpr) {
                SqlIdentifierExpr identOwner = (SqlIdentifierExpr) owner;
                String ownerName = identOwner.getName();
                this.printName(ownerName);
            } else {
                this.printExpr(owner);
            }

            this.print('.');
            this.printName(propertyExpr.getName());
        } else if (expr instanceof SqlMethodInvokeExpr) {
            this.visit((SqlMethodInvokeExpr) expr);
        } else {
            expr.accept(this);
        }
    }


    public void config(VisitorFeature feature, boolean state) {
        super.config(feature, state);
        if (feature == VisitorFeature.OutputUCase) {
            this.uppercase = state;
        } else if (feature == VisitorFeature.OutputParameterized) {
            this.parameterized = state;
        } else if (feature == VisitorFeature.OutputNameQuote) {
            this.printNameQuote = state;
        }
    }

    public void setFeatures(int features) {
        super.setFeatures(features);
        this.uppercase = this.isEnabled(VisitorFeature.OutputUCase);
        this.parameterized = this.isEnabled(VisitorFeature.OutputParameterized);
        this.printNameQuote = this.isEnabled(VisitorFeature.OutputNameQuote);
    }

    public boolean isPrettyFormat() {
        return this.isEnabled(VisitorFeature.OutputPrettyFormat);
    }

    public char getNameQuote() {
        return this.quote;
    }

    public void setNameQuote(char quote) {
        this.quote = quote;
    }

}
