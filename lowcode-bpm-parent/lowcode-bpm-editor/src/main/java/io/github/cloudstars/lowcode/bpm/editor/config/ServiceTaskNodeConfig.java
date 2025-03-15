package io.github.cloudstars.lowcode.bpm.editor.config;

/**
 * 抽象的程序节点配置
 *
 * @author clouds
 */
public abstract class ServiceTaskNodeConfig extends AbstractNodeConfig {

    @Override
    protected NodeType getType() {
        return NodeType.SERVICE;
    }

}
