package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.response.ApiResponseConfig;
import io.github.cloudstars.lowcode.commons.value.type.*;

import java.util.Arrays;

/**
 * 选项列表API配置模板
 *
 * @author clouds
 */
public class OptionListApiConfigTemplate extends AbstractApiConfigTemplate<OptionsApiConfigParams> {

    public OptionListApiConfigTemplate() {
        super("List.Options", "选项列表API配置模板");
    }

    @Override
    public ApiConfig newInstance(OptionsApiConfigParams params) {
        // 入参可以是一个可选的values属性
        ApiRequestConfig requestConfig = new ApiRequestConfig();
        ObjectPropertyConfig valuesProperty = new ObjectPropertyConfig();
        valuesProperty.setName("values");
        ArrayValueTypeConfig valuesDataType = new ArrayValueTypeConfig();
        valuesDataType.setItemsValueType(new TextValueTypeConfig());
        valuesProperty.setValueType(valuesDataType);
        ObjectValueTypeConfig requestValueType = new ObjectValueTypeConfig();
        requestValueType.setProperties(Arrays.asList(valuesProperty));
        requestConfig.setValueType(requestValueType);

        // 出参是一个列表
        ApiResponseConfig responseConfig = new ApiResponseConfig();
        TextValueTypeConfig labelValueType = new TextValueTypeConfig();
        ObjectPropertyConfig labelProperty = new ObjectPropertyConfig();
        labelProperty.setName(params.getLabelField());
        labelProperty.setValueType(labelValueType);
        XValueTypeConfig valueValueType = null;
        if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
            TextValueTypeConfig stringDataTypeConfig = new TextValueTypeConfig();
            valueValueType = stringDataTypeConfig;
        } else {
            NumberValueTypeConfig numberDataTypeConfig = new NumberValueTypeConfig();
            valueValueType = numberDataTypeConfig;
        }
        ObjectPropertyConfig responseValueProperty = new ObjectPropertyConfig();
        responseValueProperty.setName(params.getValueField());
        responseValueProperty.setValueType(valueValueType);
        ObjectValueTypeConfig optionValueType = new ObjectValueTypeConfig();
        optionValueType.setProperties(Arrays.asList(labelProperty, responseValueProperty));
        ArrayValueTypeConfig responseValueType = new ArrayValueTypeConfig();
        responseValueType.setItemsValueType(optionValueType);
        responseConfig.setValueType(responseValueType);

        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setRequest(requestConfig);
        apiConfig.setResponse(responseConfig);

        return apiConfig;
    }

}
