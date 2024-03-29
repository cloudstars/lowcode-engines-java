package net.cf.object.engine.object;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试用常量
 *
 * @author clouds
 */
public final class TestConstants {

    public final static Map<String, Object> CREATOR = new HashMap<>();
    public final static Map<String, Object> MODIFIER = new HashMap<>();

    static {

        CREATOR.put("name", "测试用户X");
        CREATOR.put("key", "testUserX");

        MODIFIER.put("name", "测试用户Y");
        MODIFIER.put("key", "testUserY");
    }

}
