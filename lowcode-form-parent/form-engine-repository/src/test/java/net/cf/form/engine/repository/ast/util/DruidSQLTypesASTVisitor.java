package net.cf.form.engine.repository.ast.util;

import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class DruidSQLTypesASTVisitor implements SQLASTVisitor {

    private final List<SQLObject> nodes = new ArrayList<>();

    public List<SQLObject> getNodes() {
        return nodes;
    }

    @Override
    public boolean visit(SQLNotExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLCharExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLBooleanExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLIntegerExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLNumberExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLNullExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLListExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLIdentifierExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLVariantRefExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLBinaryOpExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLBinaryOpExprGroup x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLMethodInvokeExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLSelectStatement x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLSelect x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLSelectItem x) {
        this.nodes.add(x);
        return true;
    }


    @Override
    public boolean visit(SQLAllColumnExpr x) {
        this.nodes.add(x);
        return true;
    }


    @Override
    public boolean visit(SQLExprTableSource x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLJoinTableSource x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLSelectGroupByClause x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLSelectOrderByItem x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLLimit x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLInsertStatement x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLInsertStatement.ValuesClause x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLUpdateStatement x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLUpdateSetItem x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SQLDeleteStatement x) {
        this.nodes.add(x);
        return true;
    }
}
