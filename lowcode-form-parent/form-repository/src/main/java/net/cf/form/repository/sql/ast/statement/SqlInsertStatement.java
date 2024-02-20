package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL插入语句
 *
 * @author 80274507
 */
public class SqlInsertStatement extends SqlInsertInto implements SqlStatement {

    public SqlInsertStatement(SqlInsertInto insertInto) {
        this.tableSource = insertInto.tableSource;
        super.addColumns(insertInto.columns);
        super.addValuesList(insertInto.valuesList);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.tableSource);
            this.nullSafeAcceptChild(visitor, this.columns);
            this.nullSafeAcceptChild(visitor, this.valuesList);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.tableSource);
        children.addAll(this.columns);
        children.addAll(this.valuesList);

        return children;
    }

    public static class ValuesClause extends AbstractSqlObjectImpl implements SqlReplaceable {

        private final List<SqlExpr> values = new ArrayList<>();

        public ValuesClause() {
        }

        public ValuesClause(List<SqlExpr> values) {
            this.values.addAll(values);
        }

        public List<SqlExpr> getValues() {
            return this.values;
        }

        public void addValues(List<SqlExpr> values) {
            for (SqlExpr value : values) {
                this.addValue(value);
            }
        }

        public SqlExpr getValue(int index) {
            return this.values.get(index);
        }

        public void addValue(SqlExpr value) {
            this.values.add(value);
            this.addChild(value);
        }

        public void addValue(int index, SqlExpr value) {
            this.values.add(index, value);
            this.addChild(value);
        }

        @Override
        protected void accept0(SqlAstVisitor visitor) {
            if (visitor.visit(this)) {
                for(int i = 0; i < this.values.size(); ++i) {
                    Object item = this.values.get(i);
                    if (item instanceof SqlObject) {
                        ((SqlObject) item).accept(visitor);
                    }
                }
            }

            visitor.endVisit(this);
        }

        @Override
        public ValuesClause cloneMe() {
            ValuesClause x = new ValuesClause();
            for (SqlExpr value : this.values) {
                x.addValue(value.cloneMe());
            }
            return x;
        }

        @Override
        public boolean replace(SqlExpr source, SqlExpr target) {
            for (int i = 0; i < this.values.size(); ++i) {
                if (values.get(i) == source) {
                    target.setParent(this);
                    this.values.set(i, target);
                    return true;
                }
            }

            return false;
        }
    }
}
