package io.github.cloudstars.lowcode.object.view;

import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfig;

/**
 * 视图接口
 *
 * @author clouds
 */
public interface ViewApi<T extends Object, R extends Object> {

    String getName();

    ValueTypeConfig getInputDataType();

    ValueTypeConfig getOutputDataType();

    R execute(T data);

}
