package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.HttpMethod;
import io.github.cloudstars.lowcode.commons.api.config.request.RequestContentTypeEnum;
import io.github.cloudstars.lowcode.commons.api.config.response.ApiResponseConfig;
import io.github.cloudstars.lowcode.commons.api.config.response.ResponseContentTypeEnum;
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
        // 请求可以是一个可选的values属性
        ApiRequestConfig apiRequestConfig = new ApiRequestConfig();
        apiRequestConfig.setMethod(HttpMethod.GET);
        apiRequestConfig.setServicePath("api://dict/{dictId}/item-list");
        apiRequestConfig.setContentType(RequestContentTypeEnum.APPLICATION_JSON);

        ObjectPropertyConfig dictIdProperty = new ObjectPropertyConfig();
        {
            dictIdProperty.setName("dictId");
            TextValueTypeConfig dictIdValueType = new TextValueTypeConfig();
            dictIdValueType.setRequired(true);
            dictIdProperty.setValueType(dictIdValueType);
        }

        ObjectPropertyConfig valuesProperty = new ObjectPropertyConfig();
        {
            valuesProperty.setName("values");
            ArrayValueTypeConfig valuesValueType = new ArrayValueTypeConfig();
            valuesValueType.setRequired(false);
            TextValueTypeConfig itemsValueType = new TextValueTypeConfig();
            itemsValueType.setRequired(true);
            valuesValueType.setItemsValueType(itemsValueType);
            valuesProperty.setValueType(valuesValueType);
        }

        ObjectValueTypeConfig requestValueType = new ObjectValueTypeConfig();
        requestValueType.setProperties(Arrays.asList(dictIdProperty, valuesProperty));
        requestValueType.setRequired(true);
        apiRequestConfig.setValueType(requestValueType);

        // 响应是一个列表List<Option<labelField, valueField>>
        ApiResponseConfig responseConfig = new ApiResponseConfig();
        responseConfig.setContentType(ResponseContentTypeEnum.APPLICATION_JSON);

        ObjectPropertyConfig labelProperty = new ObjectPropertyConfig();
        {
            labelProperty.setName(params.getLabelField());
            TextValueTypeConfig labelValueType = new TextValueTypeConfig();
            labelValueType.setRequired(true);
            labelProperty.setValueType(labelValueType);
        }

        ObjectPropertyConfig valueProperty = new ObjectPropertyConfig();
        {
            valueProperty.setName(params.getValueField());
            AbstractValueTypeConfig valueValueType = null;
            if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
                TextValueTypeConfig stringDataTypeConfig = new TextValueTypeConfig();
                valueValueType = stringDataTypeConfig;
            } else {
                NumberValueTypeConfig numberDataTypeConfig = new NumberValueTypeConfig();
                valueValueType = numberDataTypeConfig;
            }
            valueValueType.setRequired(true);
            valueProperty.setValueType(valueValueType);
        }

        ObjectValueTypeConfig optionValueType = new ObjectValueTypeConfig();
        optionValueType.setProperties(Arrays.asList(labelProperty, valueProperty));
        optionValueType.setRequired(true);

        ArrayValueTypeConfig responseValueType = new ArrayValueTypeConfig();
        responseValueType.setItemsValueType(optionValueType);
        responseValueType.setRequired(true);
        responseConfig.setValueType(responseValueType);

        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setRequest(apiRequestConfig);
        apiConfig.setResponse(responseConfig);

        return apiConfig;
    }

}
