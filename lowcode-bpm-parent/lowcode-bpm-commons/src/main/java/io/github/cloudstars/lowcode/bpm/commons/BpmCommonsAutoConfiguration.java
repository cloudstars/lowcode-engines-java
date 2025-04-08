package io.github.cloudstars.lowcode.bpm.commons;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClassFactory;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.ConditionBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.loop.LoopBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.service.ServiceTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteTaskNodeConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class BpmCommonsAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的节点配置
        NodeConfigClassFactory.register(StartNodeConfig.class);
        NodeConfigClassFactory.register(EndNodeConfig.class);
        NodeConfigClassFactory.register(UserApproveTaskNodeConfig.class);
        NodeConfigClassFactory.register(UserWriteTaskNodeConfig.class);
        NodeConfigClassFactory.register(GatewayNodeConfig.class);
        NodeConfigClassFactory.register(BranchNodeConfig.class);
        NodeConfigClassFactory.register(ConditionBranchNodeConfig.class);
        NodeConfigClassFactory.register(LoopBranchNodeConfig.class);
        NodeConfigClassFactory.register(ServiceTaskNodeConfig.class);

    }

}
