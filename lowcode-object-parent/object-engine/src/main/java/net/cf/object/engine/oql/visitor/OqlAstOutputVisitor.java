package net.cf.object.engine.oql.visitor;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.statement.DistinctOption;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.form.repository.sql.visitor.SqlAstOutputVisitor;
import net.cf.object.engine.oql.ast.*;

import java.util.List;

/**
 * 输出访问器
 *
 * @author clouds
 */
public class OqlAstOutputVisitor extends SqlAstOutputVisitor implements OqlAstVisitor {

    public OqlAstOutputVisitor(Appendable appender) {
        super(appender);
    }

    @Override
    public boolean visit(OqlFieldExpr x) {
        String owner = x.getOwner();
        if (owner != null) {
            this.print(owner);
            this.print(".");
        }

        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(OqlPropertyExpr x) {
        OqlFieldExpr owner = x.getOwner();
        if (owner != null) {
            owner.accept(this);
            this.print(".");
        }

        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(OqlObjectExpandExpr x) {
        this.print(x.getOwner());
        if (x.isStarExpanded()) {
            this.print(".*");
        } else {
            this.printParenthesesAndAcceptList(x.getFields(), ", ");
        }

        return false;
    }

    @Override
    public boolean visit(OqlExprObjectSource x) {
        x.getExpr().accept(this);
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }

    @Override
    public boolean visit(OqlSelect x) {
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
        List<OqlSelectItem> selectItems = x.getSelectItems();
        this.printAndAcceptList(selectItems, ", ");

        // 输出查询的模型
        OqlObjectSource from = x.getFrom();
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

        return false;
    }

    @Override
    public boolean visit(OqlSelectItem x) {
        x.getExpr().accept(this);
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        this.print(this.uppercase ? "INSERT INTO " : "insert into ");

        // 输出数据源
        x.getObjectSource().accept(this);

        // 输出插入的列
        this.printParenthesesAndAcceptList(x.getFields(), ", ");

        // 输出插入的列列表
        List<SqlInsertStatement.ValuesClause> valuesClauses = x.getValuesList();
        if (!valuesClauses.isEmpty()) {
            this.println();
            this.print(this.uppercase ? "VALUES " : "values ");
            this.printAndAcceptList(valuesClauses, ", ");
        }

        return false;
    }

    @Override
    public boolean visit(OqlInsertSelectStatement x) {
        this.print(this.uppercase ? "INSERT INTO " : "insert into ");

        // 输出数据源
        x.getObjectSource().accept(this);

        // 输出插入的列
        this.printParenthesesAndAcceptList(x.getFields(), ", ");

        // 输出查询条件
        this.println();
        this.visit(x.getQuery());

        return false;
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        this.print(this.uppercase ? "UPDATE " : "update ");

        // 输出数据源
        x.getObjectSource().accept(this);

        // 输出set子句
        this.print(this.uppercase ? " SET " : " set ");
        this.printAndAcceptList(x.getSetItems(), ", ");

        // 输出where条件
        this.printWhere(x.getWhere());

        return false;
    }

    @Override
    public boolean visit(OqlUpdateSetItem x) {
        x.getField().accept(this);
        this.print(" = ");
        x.getValue().accept(this);
        return false;
    }

    @Override
    public boolean visit(OqlDeleteStatement x) {
        this.print(this.uppercase ? "DELETE FROM " : "delete from ");

        // 输出表源
        x.getFrom().accept(this);

        // 输出where条件
        this.printWhere(x.getWhere());

        return false;
    }

}
