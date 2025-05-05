package io.github.cloudstars.lowcode.object.core.editor;

/**
 * 模型配置解析器
 *
 * @author clouds
 * @param <O> 模型配置类
 */
public interface XObjectConfigResolver<O extends XObjectConfig> {

    /**
     * 根据模型编号解析模型配置
     *
     * @param objectKey 模型编号
     * @return 模型配置
     */
    O resolve(String objectKey);

}
