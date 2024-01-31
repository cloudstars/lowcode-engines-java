package net.cf.form.engine.repository.sql.visitor;

import net.cf.form.engine.repository.sql.ast.SqlObject;

/**
 * 自定义输出函数接口
 *
 * @author clouds
 */
@FunctionalInterface
public interface SqlObjectPrinterFunction {

    /**
     * 自定义输出动作
     */
    void print(SqlObject object);
}
