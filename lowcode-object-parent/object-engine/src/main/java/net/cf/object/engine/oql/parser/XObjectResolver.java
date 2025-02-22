package net.cf.object.engine.oql.parser;

import net.cf.object.engine.object.XObject;

/**
 * 模型解析器
 *
 * @author 80274507
 */
public interface XObjectResolver<X extends XObject> {

    /**
     * 根据模型的名称解析模型
     *
     * @param objectName
     * @return
     */
    X resolve(String objectName);
}
