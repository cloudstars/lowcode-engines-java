package net.cf.object.engine.oql.parser;

import net.cf.object.engine.object.XObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 加了缓存的对象解析器
 *
 * 注意：仅用于单次OQL语句的解析时作缓存
 *
 * @author 80274507
 */
public class CachedObjectResolverProxy implements XObjectResolver {

    private final Map<String, XObject> resolvedObjectMap = new HashMap<>();

    private XObjectResolver resolver;

    public CachedObjectResolverProxy(XObjectResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public XObject resolve(String objectName) {
        XObject object = this.resolvedObjectMap.get(objectName);
        if (!resolvedObjectMap.containsKey(object)) {
            object = resolver.resolve(objectName);
            resolvedObjectMap.put(objectName, object);
        }

        return object;
    }

}

