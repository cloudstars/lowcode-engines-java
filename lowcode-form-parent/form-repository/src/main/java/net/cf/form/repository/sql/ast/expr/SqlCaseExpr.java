package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SqlCaseExpr extends AbstractSqlExprImpl implements SqlReplaceable {
    private static final long serialVersionUID = 1L;
    private final List<Item> items = new ArrayList();
    private SqlExpr valueExpr;
    private SqlExpr elseExpr;

    public SqlCaseExpr() {
    }

    public SqlExpr getValueExpr() {
        return this.valueExpr;
    }

    public void setValueExpr(SqlExpr valueExpr) {
        if (valueExpr != null) {
            valueExpr.setParent(this);
        }

        this.valueExpr = valueExpr;
    }

    public SqlExpr getElseExpr() {
        return this.elseExpr;
    }

    public void setElseExpr(SqlExpr elseExpr) {
        if (elseExpr != null) {
            elseExpr.setParent(this);
        }

        this.elseExpr = elseExpr;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        if (item != null) {
            item.setParent(this);
            this.items.add(item);
        }
    }

    public void addItem(SqlExpr condition, SqlExpr value) {
        this.addItem(new Item(condition, value));
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.valueExpr != null) {
                this.valueExpr.accept(visitor);
            }

            Iterator var2 = this.items.iterator();

            while(var2.hasNext()) {
                Item item = (Item)var2.next();
                if (item != null) {
                    item.accept(visitor);
                }
            }

            if (this.elseExpr != null) {
                this.elseExpr.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    public List getChildren() {
        List<SqlObject> children = new ArrayList();
        if (this.valueExpr != null) {
            children.add(this.valueExpr);
        }

        children.addAll(this.items);
        if (this.elseExpr != null) {
            children.add(this.elseExpr);
        }

        return children;
    }

    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.valueExpr == expr) {
            this.setValueExpr(target);
            return true;
        } else if (this.elseExpr == expr) {
            this.setElseExpr(target);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SqlCaseExpr _clone() {
        SqlCaseExpr x = new SqlCaseExpr();
        Iterator var2 = this.items.iterator();

        while(var2.hasNext()) {
            Item item = (Item)var2.next();
            x.addItem(item._clone());
        }

        if (this.valueExpr != null) {
            x.setValueExpr(this.valueExpr._clone());
        }

        if (this.elseExpr != null) {
            x.setElseExpr(this.elseExpr._clone());
        }

        return x;
    }

    public static class Item extends AbstractSqlObjectImpl implements SqlReplaceable {
        private SqlExpr conditionExpr;
        private SqlExpr valueExpr;

        public Item() {
        }

        public Item(SqlExpr conditionExpr, SqlExpr valueExpr) {
            this.setConditionExpr(conditionExpr);
            this.setValueExpr(valueExpr);
        }

        public SqlExpr getConditionExpr() {
            return this.conditionExpr;
        }

        public void setConditionExpr(SqlExpr conditionExpr) {
            if (conditionExpr != null) {
                conditionExpr.setParent(this);
            }

            this.conditionExpr = conditionExpr;
        }

        public SqlExpr getValueExpr() {
            return this.valueExpr;
        }

        public void setValueExpr(SqlExpr valueExpr) {
            if (valueExpr != null) {
                valueExpr.setParent(this);
            }

            this.valueExpr = valueExpr;
        }

        protected void accept0(SqlAstVisitor visitor) {
            if (visitor.visit(this)) {
                if (this.conditionExpr != null) {
                    this.conditionExpr.accept(visitor);
                }

                if (this.valueExpr != null) {
                    this.valueExpr.accept(visitor);
                }
            }

            visitor.endVisit(this);
        }

        @Override
        public Item _clone() {
            Item x = new Item();
            if (this.conditionExpr != null) {
                x.setConditionExpr(this.conditionExpr._clone());
            }

            if (this.valueExpr != null) {
                x.setValueExpr(this.valueExpr._clone());
            }

            return x;
        }

        public boolean replace(SqlExpr expr, SqlExpr target) {
            if (this.valueExpr == expr) {
                this.setValueExpr(target);
                return true;
            } else if (this.conditionExpr == expr) {
                this.setConditionExpr(target);
                return true;
            } else {
                return false;
            }
        }
    }
}
