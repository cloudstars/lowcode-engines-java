package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.AbstractOqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL select查询
 *
 * @author clouds
 */
public class OqlSelect extends AbstractOqlObjectImpl {

    /**
     * 查询的列表
     */
    private List<OqlSelectItem> selectItems = new ArrayList<>();

    /**
     * 查询的对象
     */
    private OqlObjectSource from;

    /**
     * 查询条件
     */
    private OqlWhereClause where;

    /**
     * Group By 子句
     */
    private OqlSelectGroupBy groupBy;

    private OqlSelectOrderBy orderBy;

    private OqlSelectLimit limit;

    public List<OqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<OqlSelectItem> selectItems) {
        this.selectItems = selectItems;
        this.addChildren(selectItems);
    }

    public OqlObjectSource getFrom() {
        return from;
    }

    public void setFrom(OqlObjectSource from) {
        this.from = from;
        this.addChild(from);
    }

    public OqlWhereClause getWhere() {
        return where;
    }

    public void setWhere(OqlWhereClause where) {
        this.where = where;
        this.addChild(where);
    }

    public OqlSelectGroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(OqlSelectGroupBy groupBy) {
        this.groupBy = groupBy;
        this.addChild(groupBy);
    }

    public OqlSelectOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OqlSelectOrderBy orderBy) {
        this.orderBy = orderBy;
        this.addChild(orderBy);
    }

    public OqlSelectLimit getLimit() {
        return limit;
    }

    public void setLimit(OqlSelectLimit limit) {
        this.limit = limit;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.selectItems != null) {
                this.acceptChildren(visitor, this.selectItems);
            }

            if (this.from != null) {
                this.from.accept(visitor);
            }

            if (this.where != null) {
                this.where.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlSelect clone() {
        OqlSelect select = new OqlSelect();
        List<OqlSelectItem> cloneItems = new ArrayList<>();
        for (OqlSelectItem selectItem : selectItems) {
            cloneItems.add(selectItem.clone());
        }
        select.setSelectItems(cloneItems);
        select.setFrom(this.from._clone());
        select.setWhere(this.where.clone());

        return null;
    }

    @Override
    public List<OqlObject> getChildren() {
        List<OqlObject> children = new ArrayList<>();
        children.addAll(this.selectItems);
        children.add(this.from);
        children.add(this.where);
        children.add(this.groupBy);
        children.add(this.orderBy);
        children.add(this.limit);
        return children;
    }
}
