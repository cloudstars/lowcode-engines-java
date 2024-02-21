package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlJoinTableSource extends AbstractSqlTableSourceImpl implements SqlReplaceable {

    protected SqlTableSource left;
    protected JoinType joinType;

    protected SqlTableSource right;

    protected SqlExpr condition;

    protected final List<SqlExpr> using = new ArrayList();


    public SqlJoinTableSource(String alias) {
        super(alias);
    }

    public SqlJoinTableSource() {
    }

    public SqlJoinTableSource(SqlTableSource left, JoinType joinType, SqlTableSource right, SqlExpr condition) {
        this.setLeft(left);
        this.setJoinType(joinType);
        this.setRight(right);
        this.setCondition(condition);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.left != null) {
                this.left.accept(visitor);
            }

            if (this.right != null) {
                this.right.accept(visitor);
            }

            if (this.condition != null) {
                this.condition.accept(visitor);
            }

            for (int i = 0; i < this.using.size(); ++i) {
                SqlExpr item = this.using.get(i);
                if (item != null) {
                    item.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    public JoinType getJoinType() {
        return this.joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public void setImplicitJoinToCross() {
        if (this.joinType == SqlJoinTableSource.JoinType.COMMA) {
            this.joinType = SqlJoinTableSource.JoinType.CROSS_JOIN;
        }

        if (this.left instanceof SqlJoinTableSource) {
            ((SqlJoinTableSource) this.left).setImplicitJoinToCross();
        }

        if (this.right instanceof SqlJoinTableSource) {
            ((SqlJoinTableSource) this.right).setImplicitJoinToCross();
        }

    }

    public SqlTableSource getLeft() {
        return this.left;
    }

    public void setLeft(SqlTableSource left) {
        if (left != null) {
            left.setParent(this);
        }

        this.left = left;
    }

    public void setLeft(String tableName, String alias) {
        SqlExprTableSource tableSource;
        if (tableName != null && tableName.length() != 0) {
            tableSource = new SqlExprTableSource(new SqlIdentifierExpr(tableName), alias);
        } else {
            tableSource = null;
        }

        this.setLeft(tableSource);
    }

    public void setRight(String tableName, String alias) {
        SqlExprTableSource tableSource;
        if (tableName != null && tableName.length() != 0) {
            tableSource = new SqlExprTableSource(new SqlIdentifierExpr(tableName), alias);
        } else {
            tableSource = null;
        }

        this.setRight(tableSource);
    }

    public SqlTableSource getRight() {
        return this.right;
    }

    public void setRight(SqlTableSource right) {
        if (right != null) {
            right.setParent(this);
        }

        this.right = right;
    }

    public SqlExpr getCondition() {
        return this.condition;
    }

    public void setCondition(SqlExpr condition) {
        if (condition != null) {
            condition.setParent(this);
        }

        this.condition = condition;
    }

    public void addCondition(SqlExpr condition) {
        if (this.condition == null) {
            this.condition = condition;
            this.setImplicitJoinToCross();
        } else {
            this.condition = SqlBinaryOpExpr.and(this.condition, condition);
        }
    }

    public void addConditionnIfAbsent(SqlExpr condition) {
        if (!this.containsCondition(condition)) {
            this.condition = SqlBinaryOpExpr.and(this.condition, condition);
        }
    }

    public boolean containsCondition(SqlExpr condition) {
        if (this.condition == null) {
            return false;
        } else if (this.condition.equals(condition)) {
            return false;
        } else {
            return this.condition instanceof SqlBinaryOpExpr ? ((SqlBinaryOpExpr) this.condition).contains(condition) : false;
        }
    }

    public List<SqlExpr> getUsing() {
        return this.using;
    }

    /*public void output(Appendable buf) {
        try {
            this.left.output(buf);
            buf.append(' ');
            buf.append(SqlJoinTableSource.JoinType.toString(this.joinType));
            buf.append(' ');
            this.right.output(buf);
            if (this.condition != null) {
                buf.append(" ON ");
                this.condition.output(buf);
            }

        } catch (IOException var3) {
            throw new FastsqlException("output error", var3);
        }
    }*/

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.condition == expr) {
            this.setCondition(target);
            return true;
        } else {
            for (int i = 0; i < this.using.size(); ++i) {
                if (this.using.get(i) == expr) {
                    target.setParent(this);
                    this.using.set(i, target);
                    return true;
                }
            }

            return false;
        }
    }

    public boolean replace(SqlTableSource cmp, SqlTableSource target) {
        if (this.left == cmp) {
            if (target == null) {
                SqlUtils.replaceInParent(this, this.right);
            } else {
                this.setLeft(target);
            }

            return true;
        } else if (this.right == cmp) {
            if (target == null) {
                SqlUtils.replaceInParent(this, this.left);
            } else {
                this.setRight(target);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public SqlJoinTableSource cloneMe() {
        SqlJoinTableSource x = new SqlJoinTableSource();
        x.alias = this.alias;
        if (this.left != null) {
            x.setLeft(this.left.cloneMe());
        }

        x.joinType = this.joinType;
        if (this.right != null) {
            x.setRight(this.right.cloneMe());
        }

        if (this.condition != null) {
            x.setCondition(this.condition.cloneMe());
        }

        for (SqlExpr item : this.using) {
            SqlExpr item2 = item.cloneMe();
            item2.setParent(x);
            x.using.add(item2);
        }

        return x;
    }

    public void reverse() {
        SqlTableSource temp = this.left;
        this.left = this.right;
        this.right = temp;
        if (this.left instanceof SqlJoinTableSource) {
            ((SqlJoinTableSource) this.left).reverse();
        }

        if (this.right instanceof SqlJoinTableSource) {
            ((SqlJoinTableSource) this.right).reverse();
        }

    }

    public boolean contains(SqlTableSource tableSource, SqlExpr condition) {
        if (this.right.equals(tableSource)) {
            if (this.condition == condition) {
                return true;
            } else {
                return this.condition != null && this.condition.equals(condition);
            }
        } else if (this.left instanceof SqlJoinTableSource) {
            SqlJoinTableSource joinLeft = (SqlJoinTableSource) this.left;
            if (tableSource instanceof SqlJoinTableSource) {
                SqlJoinTableSource join = (SqlJoinTableSource) tableSource;
                if (join.right.equals(this.right) && this.condition.equals(condition) && joinLeft.right.equals(join.left)) {
                    return true;
                }
            }

            return joinLeft.contains(tableSource, condition);
        } else {
            return false;
        }
    }

    public boolean contains(SqlTableSource tableSource, SqlExpr condition, JoinType joinType) {
        if (this.right.equals(tableSource)) {
            if (this.condition == condition) {
                return true;
            } else {
                return this.condition != null && this.condition.equals(condition) && this.joinType == joinType;
            }
        } else if (this.left instanceof SqlJoinTableSource) {
            SqlJoinTableSource joinLeft = (SqlJoinTableSource) this.left;
            if (tableSource instanceof SqlJoinTableSource) {
                SqlJoinTableSource join = (SqlJoinTableSource) tableSource;
                if (join.right.equals(this.right) && this.condition != null && this.condition.equals(join.condition) && joinLeft.right.equals(join.left) && this.joinType == join.joinType && joinLeft.condition != null && joinLeft.condition.equals(condition) && joinLeft.joinType == joinType) {
                    return true;
                }
            }

            return joinLeft.contains(tableSource, condition, joinType);
        } else {
            return false;
        }
    }

    public SqlJoinTableSource findJoin(SqlTableSource tableSource, JoinType joinType) {
        if (this.right.equals(tableSource)) {
            return this.joinType == joinType ? this : null;
        } else {
            return this.left instanceof SqlJoinTableSource ? ((SqlJoinTableSource) this.left).findJoin(tableSource, joinType) : null;
        }
    }

    public boolean containsAlias(String alias) {
        if (SqlUtils.nameEquals(this.alias, alias)) {
            return true;
        } else if (this.left != null && this.left.containsAlias(alias)) {
            return true;
        } else {
            return this.right != null && this.right.containsAlias(alias);
        }
    }

    public boolean match(String aliasA, String aliasB) {
        if (this.left != null && this.right != null) {
            if (this.left.containsAlias(aliasA) && this.right.containsAlias(aliasB)) {
                return true;
            } else {
                return this.right.containsAlias(aliasA) && this.left.containsAlias(aliasB);
            }
        } else {
            return false;
        }
    }

    public SqlJoinTableSource join(SqlTableSource right, JoinType joinType, SqlExpr condition) {
        SqlJoinTableSource joined = new SqlJoinTableSource(this, joinType, right, condition);
        return joined;
    }

    public SqlTableSource other(SqlTableSource x) {
        if (this.left == x) {
            return this.right;
        } else {
            return this.right == x ? this.left : null;
        }
    }

    public void splitTo(List<SqlTableSource> outTableSources, JoinType joinType) {
        if (joinType == this.joinType) {
            if (this.left instanceof SqlJoinTableSource) {
                ((SqlJoinTableSource) this.left).splitTo(outTableSources, joinType);
            } else {
                outTableSources.add(this.left);
            }

            if (this.right instanceof SqlJoinTableSource) {
                ((SqlJoinTableSource) this.right).splitTo(outTableSources, joinType);
            } else {
                outTableSources.add(this.right);
            }
        } else {
            outTableSources.add(this);
        }

    }

    public static enum JoinType {
        COMMA(","),
        JOIN("JOIN"),
        INNER_JOIN("INNER JOIN"),
        CROSS_JOIN("CROSS JOIN"),
        NATURAL_JOIN("NATURAL JOIN"),
        LEFT_OUTER_JOIN("LEFT JOIN"),
        RIGHT_OUTER_JOIN("RIGHT JOIN");

        public final String name;
        public final String nameLCase;

        private JoinType(String name) {
            this.name = name;
            this.nameLCase = name.toLowerCase();
        }

        public static String toString(JoinType joinType) {
            return joinType.name;
        }
    }

}
