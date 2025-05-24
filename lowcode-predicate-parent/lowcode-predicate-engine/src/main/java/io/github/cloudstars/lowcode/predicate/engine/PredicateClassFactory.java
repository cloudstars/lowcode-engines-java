package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 断言类工厂
 *
 * @author clouds
 */
public class PredicateClassFactory {

    /**
     * key是断言配置，值是断言实现的Java类的映射表
     */
    private static final Map<Class<? extends XPredicateConfig>, Class<? extends XPredicate>> PREDICATE_CALSS_MAP = new HashMap<>();


    private PredicateClassFactory() {
    }

    /**
     * 注册一种断言类
     *
     * @param predicateClass 断言类
     */
    public static void register(Class<? extends XPredicate> predicateClass) {
        String typeName = predicateClass.getName();
        PredicateClass[] annotations = predicateClass.getAnnotationsByType(PredicateClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("断言" + typeName + "必须添加注解@PredicateClass，注册失败！");
        }

        Class<? extends XPredicateConfig> predicateConfigClass = annotations[0].predicateConfigClass();
        try {
            predicateClass.getConstructor(predicateConfigClass);
            PREDICATE_CALSS_MAP.put(predicateConfigClass, predicateClass);
        } catch (Exception e) {
            String predicateConfigClassName = predicateConfigClass.getName();
            throw new RuntimeException("断言" + typeName + "必须有一个带" + predicateConfigClassName + "参数的public构造函数！");
        }
    }


    /**
     * 根据断言的Java类获取断言的Java类
     *
     * @param predicateConfigClass 断言配置Java类
     * @return 断言Java类
     */
    public static Class<? extends XPredicate> get(Class<? extends XPredicateConfig> predicateConfigClass) {
        Class<? extends XPredicate> predicateClass = PREDICATE_CALSS_MAP.get(predicateConfigClass);
        if (predicateClass == null) {
            throw new RuntimeException("断言[" + predicateConfigClass.getName() + "]不存在！");
        }

        return predicateClass;
    }
}
