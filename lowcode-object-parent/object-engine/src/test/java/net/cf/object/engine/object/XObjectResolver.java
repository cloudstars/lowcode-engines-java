package net.cf.object.engine.object;

/**
 * XObject解析器
 *
 * @author clouds
 */
public interface XObjectResolver {

    /**
     * 解析XObject
     *
     * @param objectName 业务模型的名称
     * @return
     */
    XObject resolveObject(String objectName);
}
