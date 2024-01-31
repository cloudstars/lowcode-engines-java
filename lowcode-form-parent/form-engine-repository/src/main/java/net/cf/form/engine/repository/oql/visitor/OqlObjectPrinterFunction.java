package net.cf.form.engine.repository.oql.visitor;

import net.cf.form.engine.repository.oql.ast.OqlObject;

/**
 * 自定义输出函数接口
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface OqlObjectPrinterFunction {

    /**
     * 自定义输出动作
     */
    void print(OqlObject object);
}
