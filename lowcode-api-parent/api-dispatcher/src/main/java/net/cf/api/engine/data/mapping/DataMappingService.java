package net.cf.api.engine.data.mapping;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据类型映射服务
 * @author 80345746
 * @version v1.0
 * @date 2024/1/25 15:00
 */
public class DataMappingService {

    private Map<TypeBind, DataMappingHandler> dataMappingHandlers;

    /**
     * 构造函数
     * @param dataMappingHandlers  注入数据类型映射handler
     */
    public DataMappingService(List<DataMappingHandler> dataMappingHandlers) {
        Map<TypeBind, DataMappingHandler> map = dataMappingHandlers.stream().collect(Collectors.toMap(e -> e.getTypeBind(), e -> e));
        this.dataMappingHandlers = map;
    }

    /**
     * 基于schema转换类型
     * @param jsonSchema schema
     * @param input 输入
     * @return 输出
     */
    public Object convert(JSONObject jsonSchema, Object input) {
        // 需要优化遍历schema的逻辑，基于对象去做遍历，而非map
        Map<String, String> paramTypeMap = getParamTypes(jsonSchema);
        return typeMapping(paramTypeMap, input);
    }

    /**
     * 获取参数路径path和类型
     * @param jsonSchema schema
     * @return 返回
     */
    public Map<String, String> getParamTypes(JSONObject jsonSchema) {
        Map<String, String> result = new HashMap<>();
        if (CollectionUtils.isEmpty(jsonSchema)) {
            return result;
        }
        ArrayList<Object> path = new ArrayList<>();
        path.add("$");
        dsf(jsonSchema, path, result);
        return result;
    }

    /**
     * 深度优先遍历json
     * @param jsonSchema schema
     * @param path 路径
     * @param result 结果
     */
    public void dsf(JSONObject jsonSchema, ArrayList<Object> path, Map<String, String> result) {
        if (CollectionUtils.isEmpty(jsonSchema)) {
            return;
        }
        String type = jsonSchema.getString("type");
        boolean isArray = "array".equalsIgnoreCase(type);
        boolean isObject = "object".equalsIgnoreCase(type);

        if (isArray) {
            Object last = path.remove(path.size() - 1);
            last = last + "[*]";
            path.add(last);
            JSONObject items = jsonSchema.getJSONObject("items");
            dsf(items, path, result);
            path.remove(path.size() - 1);
            return;
        }
        if (isObject) {
            JSONObject properties = jsonSchema.getJSONObject("properties");
            properties.forEach((k, v) -> {
                path.add(k);
                dsf((JSONObject) v, path, result);
                path.remove(path.size() - 1);
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        path.forEach(e -> sb.append(e).append("."));
        sb.setLength(sb.length() - 1);
        result.put(sb.toString(), type);
    }


    /**
     * 类型映射
     * @param paramTypeMap 参数路径
     * @param input 请求
     * @return 结果
     */
    public Object typeMapping(Map<String, String> paramTypeMap, Object input) {
        paramTypeMap.forEach((k, v) -> {
            Object eval = JSONPath.eval(input, k);
            if (eval instanceof List) {
                List<?> list = (List<?>) eval;
                List<Object> newList = new ArrayList<>();
                for (Object elem : list) {
                    newList.add(mapping(v, elem));
                }
                JSONPath.set(input, k, newList);
            } else {
                JSONPath.set(input, k, mapping(v, eval));
            }
        });
        return input;
    }

    /**
     * 单个字段类型绑定处理
     * @param targetType 目标类型
     * @param obj 原始数据
     * @return 返回结果
     */
    public Object mapping(String targetType, Object obj) {
        String sourceType = obj.getClass().getSimpleName();
        TypeBind typeBind = new TypeBind(targetType, sourceType);
        DataMappingHandler dataMappingHandler = dataMappingHandlers.get(typeBind);
        if (dataMappingHandler != null) {
            return dataMappingHandler.handle(obj);
        }
        return obj;
    }
}
