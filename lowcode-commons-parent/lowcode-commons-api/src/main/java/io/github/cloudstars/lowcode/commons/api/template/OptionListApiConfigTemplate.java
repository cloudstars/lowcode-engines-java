package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiInputConfig;
import io.github.cloudstars.lowcode.commons.api.config.ApiOutputConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.*;

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
        ApiInputConfig inputConfig = new ApiInputConfig();
        ObjectValueProperty valuesProperty = new ObjectValueProperty();
        valuesProperty.setCode("values");
        ArrayValueTypeConfig valuesDataType = new ArrayValueTypeConfig();
        valuesDataType.setItemsValueType(new TextValueTypeConfig());
        valuesProperty.setValueType(valuesDataType);
        ObjectValueTypeConfig inputValueType = new ObjectValueTypeConfig();
        inputValueType.setProperties(Arrays.asList(valuesProperty));
        inputConfig.setValueType(inputValueType);

        // 出参是一个列表
        ApiOutputConfig outputConfig = new ApiOutputConfig();
        TextValueTypeConfig labelValueType = new TextValueTypeConfig();
        ObjectValueProperty labelProperty = new ObjectValueProperty();
        labelProperty.setCode(params.getLabelField());
        labelProperty.setValueType(labelValueType);
        XValueTypeConfig valueValueType = null;
        if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
            TextValueTypeConfig stringDataTypeConfig = new TextValueTypeConfig();
            valueValueType = stringDataTypeConfig;
        } else {
            NumberValueTypeConfig numberDataTypeConfig = new NumberValueTypeConfig();
            valueValueType = numberDataTypeConfig;
        }
        ObjectValueProperty valueProperty = new ObjectValueProperty();
        valueProperty.setCode(params.getValueField());
        valueProperty.setValueType(valueValueType);
        ObjectValueTypeConfig optionValueType = new ObjectValueTypeConfig();
        optionValueType.setProperties(Arrays.asList(labelProperty, valueProperty));
        ArrayValueTypeConfig outputValueType = new ArrayValueTypeConfig();
        outputValueType.setItemsValueType(optionValueType);
        outputConfig.setValueType(outputValueType);

        ApiConfig apiConfig = new ApiConfig(inputConfig, outputConfig);
        return apiConfig;
    }

}
