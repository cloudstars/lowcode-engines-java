package net.cf.form.repository.ast.util;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.op.SqlListExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlTypesAstVisitor implements SqlAstVisitor {

    private final List<SqlObject> nodes = new ArrayList<>();

    public List<SqlObject> getNodes() {
        return nodes;
    }


    @Override
    public boolean visit(SqlCharExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlBooleanExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlIntegerExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlDecimalExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlNullExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlListExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlVariantRefExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlBinaryOpExprGroup x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlMethodInvokeExpr x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlSelectStatement x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlSelect x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlSelectItem x) {
        this.nodes.add(x);
        return true;
    }


    @Override
    public boolean visit(SqlAllColumnExpr x) {
        this.nodes.add(x);
        return true;
    }


    @Override
    public boolean visit(SqlExprTableSource x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlSelectGroupByClause x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlOrderBy x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlSelectOrderByItem x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlLimit x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlInsertStatement x) {
        this.nodes.add(x);
        return true;
    }
    @Override
    public boolean visit(SqlInsertStatement.ValuesClause x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlUpdateStatement x) {
        this.nodes.add(x);
        return true;
    }

    @Override
    public boolean visit(SqlUpdateSetItem x) {
        return SqlAstVisitor.super.visit(x);
    }

    @Override
    public void endVisit(SqlUpdateSetItem x) {
        SqlAstVisitor.super.endVisit(x);
    }

    @Override
    public boolean visit(SqlDeleteStatement x) {
        return SqlAstVisitor.super.visit(x);
    }

    @Override
    public void endVisit(SqlDeleteStatement x) {
        SqlAstVisitor.super.endVisit(x);
    }
}
