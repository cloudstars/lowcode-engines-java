package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class SqlSubQueryTableSource extends AbstractSqlTableSourceImpl {

    protected SqlSelect select;

    //protected List<SqlName> columns;

    public SqlSubQueryTableSource() {
        //this.columns = new ArrayList();
    }

    public SqlSubQueryTableSource(String alias) {
        super(alias);
        //this.columns = new ArrayList();
    }

    public SqlSubQueryTableSource(SqlSelect select) {
        //this.columns = new ArrayList();
        this.setSelect(select);
    }

    public SqlSubQueryTableSource(SqlSelect select, String alias) {
        super(alias);
        //this.columns = new ArrayList();
        this.setSelect(select);
    }

    public SqlSelect getSelect() {
        return this.select;
    }

    public void setSelect(SqlSelect x) {
        if (x != null) {
            x.setParent(this);
        }

        this.select = x;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.select != null) {
                this.select.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlSubQueryTableSource cloneMe() {
        SqlSubQueryTableSource x = new SqlSubQueryTableSource();
        x.alias = this.alias;
        if (this.select != null) {
            x.select = this.select.cloneMe();
            x.select.setParent(x);
        }

        /*for (SqlName column : this.columns) {
            SqlName c2 = column._clone();
            c2.setParent(x);
            x.columns.add(c2);
        }*/

        return x;
    }
}
