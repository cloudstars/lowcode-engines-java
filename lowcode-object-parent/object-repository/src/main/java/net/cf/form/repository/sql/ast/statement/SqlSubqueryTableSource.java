package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class SqlSubqueryTableSource extends AbstractSqlTableSourceImpl {

    protected SqlSelect select;

    //protected List<SqlName> columns;

    public SqlSubqueryTableSource() {
        //this.columns = new ArrayList();
    }

    public SqlSubqueryTableSource(String alias) {
        super(alias);
        //this.columns = new ArrayList();
    }

    public SqlSubqueryTableSource(SqlSelect select) {
        //this.columns = new ArrayList();
        this.setSelect(select);
    }

    public SqlSubqueryTableSource(SqlSelect select, String alias) {
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
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.select != null) {
                this.select.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlSubqueryTableSource cloneMe() {
        SqlSubqueryTableSource x = new SqlSubqueryTableSource();
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
