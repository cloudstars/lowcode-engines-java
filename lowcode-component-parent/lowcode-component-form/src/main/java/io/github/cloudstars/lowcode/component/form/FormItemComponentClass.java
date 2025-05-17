package io.github.cloudstars.lowcode.component.form;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormItemComponentClass {

    /**
     * 表单项组件的类型
     *
     * @return 表单项组件的类型
     */
    String type();

    /**
     * 是否只读组件
     *
     * @return 是否只读
     */
    boolean readonly() default true;

}
