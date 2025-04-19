package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.*;

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
        ObjectValueProperty valuesProperty = new ObjectValueProperty();
        valuesProperty.setName("values");
        ArrayValueTypeConfig valuesDataType = new ArrayValueTypeConfig();
        valuesDataType.setItemsDataType(new TextValueTypeConfig());
        valuesProperty.setValueType(valuesDataType);
        ObjectValueTypeConfig inputDataType = new ObjectValueTypeConfig();
        inputDataType.setProperties(Arrays.asList(valuesProperty));

        // 出参是一个列表
        TextValueTypeConfig labelDataType = new TextValueTypeConfig();
        ObjectValueProperty labelProperty = new ObjectValueProperty(params.getLabelField(), labelDataType);
        XValueTypeConfig valueDataType = null;
        if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
            TextValueTypeConfig stringDataTypeConfig = new TextValueTypeConfig();
            valueDataType = stringDataTypeConfig;
        } else {
            NumberValueTypeConfig numberDataTypeConfig = new NumberValueTypeConfig();
            valueDataType = numberDataTypeConfig;
        }
        ObjectValueProperty valueProperty = new ObjectValueProperty(params.getValueField(), valueDataType);
        ObjectValueTypeConfig optionDataType = new ObjectValueTypeConfig();
        optionDataType.setProperties(Arrays.asList(labelProperty, valueProperty));
        ArrayValueTypeConfig outputDataType = new ArrayValueTypeConfig();
        outputDataType.setItemsDataType(optionDataType);

        ApiConfig apiConfig = new ApiConfig(inputDataType, outputDataType);
        return apiConfig;
    }

}
