package io.github.cloudstars.lowcode.formula.parser.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 函数配置工厂
 *
 * @author clouds 
 */
public final class FunctionConfigFactory {

    /**
     * 函数名到函数配置的映射
     */
    private static final Map<String, FunctionConfig> CONFIG_MAP = new HashMap<>();

    /**
     * 注册一个函数
     *
     * @param descriptor 函数配置
     */
    public static void register(FunctionConfig descriptor) {
        List<FunctionParam> params = descriptor.getParams();
        if (params != null) {
            boolean hasNotRequired = false;
            for (FunctionParam param : params) {
                if (param.isRequired() && hasNotRequired) {
                    throw new FunctionException("函数的参数中必填的参数必须出现在非必填的参数前面，请检查：" + descriptor.toJson());
                }
                hasNotRequired = !param.isRequired();
            }
        }

        CONFIG_MAP.put(descriptor.getName(), descriptor);
    }

    /**
     * 根据函数名称获取函数配置
     *
     * @param funcName 函数名称
     * @return 函数配置
     */
    public static FunctionConfig get(String funcName) {
        return CONFIG_MAP.get(funcName);
    }
}
