package net.cf.api.flow.engine.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public class ActionBuilder {

    private final static Map<String, Constructor<AbstractAction>> ACTION_MAP = new HashMap<>();

    static {
        buildActionConstructorMap();
    }

    public static List<AbstractAction> build(JSONArray children) {
        List<AbstractAction> actions = new ArrayList<>(children.size());
        children.forEach(child -> actions.add(buildChild((JSONObject) child)));
        return actions;
    }

    private static AbstractAction buildChild(JSONObject child) {
        String type = child.getString("type");
        if (type.isEmpty()) {
            throw new RuntimeException("schema不合法，缺少type字段：" + child);
        }

        Constructor<AbstractAction> constructor = ACTION_MAP.get(type);
        if (constructor == null) {
            throw new RuntimeException("不合法的操作类型：" + type + "， config：" + child);
        }

        try {
            return constructor.newInstance(child);
        } catch (Exception e) {
            throw new RuntimeException("构建Action实例失败，" + "config：" + child);
        }
    }

    /**
     * 构建一个操作类型到Action类构建函数的映射
     */
    private static void buildActionConstructorMap() {
        String scanClasspath = ActionBuilder.class.getPackage().getName();
        List<Class<AbstractAction>> actionClasses = AnnotationScanner.scan(AbstractAction.class, scanClasspath);
        for (Class<AbstractAction> actionClass : actionClasses) {
            if (Modifier.isAbstract(actionClass.getModifiers())) {
                // 忽略抽象类
                continue;
            }

            String className = actionClass.getName();
            Constructor<AbstractAction> constructor;
            try {
                constructor = actionClass.getConstructor(JSONObject.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不同类" + className + "的具有JSONObject参数的公有构造函数");
            }

            ActionAnno action = actionClass.getAnnotation(ActionAnno.class);
            if (action == null) {
                throw new RuntimeException(className + "类上找不到Action注解");
            }

            String type = action.type();
            if (ACTION_MAP.containsKey(type)) {
                throw new RuntimeException(className + "类上的Action注解的type已经存在");
            }

            ACTION_MAP.put(type, constructor);
        }
    }
}
