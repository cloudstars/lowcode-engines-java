package io.github.cloudstars.lowcode.commons.editor;

public class ConfigParser {

    public static <T extends XConfig> T parse(Class<T> tClass, String jsonString) {
        return null;

    }

    /*
    private static <T extends XConfig> T newConfig() {
        T config = null;
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            config = constructor.newInstance();
        } catch (Exception e) {
            String className = tClass.getName();
            throw new RuntimeException("类" + className + "必须包含一个public的无参构造函数");
        }
    }*/
}
