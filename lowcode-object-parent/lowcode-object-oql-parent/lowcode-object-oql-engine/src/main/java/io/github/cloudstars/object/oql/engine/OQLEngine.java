package io.github.cloudstars.object.oql.engine;

import java.util.Map;

/**
 * OQL引擎
 *
 * @author clouds
 */
public interface OQLEngine {

    int insert(String oql);

    int insert(String oql, Map<String, Object> paramMap);

    int update(String oql);

    int update(String oql, Map<String, Object> paramMap);

}
