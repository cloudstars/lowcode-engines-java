package io.github.cloudstars.lowcode.bpm.commons.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeConfigClass {

    /**
     * 节点类型的名称
     *
     * @return 节点类型的名称
     */
    String type();

}
