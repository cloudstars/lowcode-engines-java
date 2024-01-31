package net.cf.form.engine.repository.mysql.statement.delete;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.data.DetailDataField;
import net.cf.form.engine.repository.sql_bak.DeleteDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;

/**
 * 用于生成 MySql delete 语句的AST访问器
 *
 * @author clouds
 */
public final class DeleteSqlAstVisitor extends AbstractSqlAstVisitor<DeleteSqlInfo> {

    public DeleteSqlAstVisitor(DataObjectResolver resolver) {
        this(resolver, false);
    }

    public DeleteSqlAstVisitor(DataObjectResolver resolver, boolean parameterized) {
        super(resolver, parameterized);
    }

    @Override
    public boolean visit(OqlDeleteStatement x) {
        OqlObjectSource objectSource = x.getObjectSource();
        this.resolveObjectSource(objectSource);
        this.sqlInfo = new DeleteSqlInfo(this.object);


        this.checkFieldValues(x);
        this.parseSqlInfo(x);

        return true;
    }


    /**
     * 检查字段
     *
     * @param statement
     */
    private void checkFieldValues(OqlDeleteStatement statement) {
        // todo 添加字段检查
    }

    /**
     * 解析SQL信息
     *
     * @param statement
     */
    private void parseSqlInfo(OqlDeleteStatement statement) {
        for (DataField dataField : this.object.getFields()) {
            if (!(dataField instanceof DetailDataField)) {
                continue;
            }
            DetailDataField detailDataField = (DetailDataField) dataField;
            DataObject dataObject = this.resolveFieldObject(dataField.getName());
            DeleteDetailSqlInfo deleteDetailSqlInfo = new DeleteDetailSqlInfo(dataObject, this.sqlInfo);
            this.sqlInfo.addDetailSqlInfo(detailDataField.getName(), deleteDetailSqlInfo);
        }
        this.sqlInfo.setWhereClause(statement.getWhereClause());
    }
}
