//package io.github.cloudstars.lowcode.commons.lang.json;
//
//import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//
///**
// * JSON工具类
// *
// * @author clouds
// */
//public final class JsonConfigUtils {
//
//    private JsonConfigUtils() {
//    }
//
//
//    /**
//     * 将配置列表转换为JsonArray
//     *
//     * @param configs 配置列表
//     * @return JsonArray
//     * @param <T> 配置类型
//     */
//    public static <T extends XConfig> JsonArray toJsonArray(List<T> configs) {
//        JsonArray jsonArray = new JsonArray();
//        configs.forEach(config -> {
//            jsonArray.add(config.toJson());
//        });
//
//        return jsonArray;
//    }
//
//    /**
//     * 将配置列表转换为JsonArray
//     *
//     * @param jsonArray 配置列表
//     * @return JsonArray
//     * @param <T> 配置类型
//     */
//    public static <T extends XConfig> List<T> fromJsonArray(JsonArray jsonArray, Function<JsonObject, T> function) {
//        List<T> configs = new ArrayList<>();
//        jsonArray.forEach(jsonItem -> configs.add(function.apply((JsonObject) jsonItem)));
//
//        return configs;
//    }
//}
