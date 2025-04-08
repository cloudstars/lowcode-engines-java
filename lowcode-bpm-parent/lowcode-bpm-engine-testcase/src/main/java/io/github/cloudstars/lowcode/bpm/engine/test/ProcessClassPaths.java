package io.github.cloudstars.lowcode.bpm.engine.test;

/**
 * 流程配置所在的类路径定义
 *
 * @author clouds
 */
public interface ProcessClassPaths {

    /**
     * 简单的不带分支的用户节点流程配置
     */
    String USER_SIMPLE1 = "process/user/user-simple1.json";


    /**
     * 简单的含分支的用户节点流程配置
     */
    String USER_SIMPLE_BRANCH = "process/user/user-simple-branch.json";

    /**
     * 简单的不带分支的程序节点流程配置
     */
    String SERVICE_SIMPLE1 = "process/service/service-simple1.json";


    /**
     * 简单的不含嵌套循环的流程配置
     */
    String LOOP_SIMPLE1 = "process/loop/loop-simple1.json";

}
