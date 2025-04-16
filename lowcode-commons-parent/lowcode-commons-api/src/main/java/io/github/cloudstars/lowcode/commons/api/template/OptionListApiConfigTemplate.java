package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.data.type.*;

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
        valuesDataType.setItemsDataType(new StringValueTypeConfig());
        valuesProperty.setValueType(valuesDataType);
        ObjectValueTypeConfig inputDataType = new ObjectValueTypeConfig();
        inputDataType.setProperties(Arrays.asList(valuesProperty));

        // 出参是一个列表
        StringValueTypeConfig labelDataType = new StringValueTypeConfig();
        ObjectValueProperty labelProperty = new ObjectValueProperty(params.getLabelField(), labelDataType);
        ValueTypeConfig valueDataType = null;
        if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
            StringValueTypeConfig stringDataTypeConfig = new StringValueTypeConfig();
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
