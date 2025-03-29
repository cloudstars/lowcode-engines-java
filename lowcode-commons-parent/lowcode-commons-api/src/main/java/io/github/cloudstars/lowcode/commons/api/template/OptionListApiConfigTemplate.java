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
        ObjectProperty valuesProperty = new ObjectProperty();
        valuesProperty.setName("values");
        ArrayDataTypeConfig valuesDataType = new ArrayDataTypeConfig();
        valuesDataType.setItemsDataType(new StringDataTypeConfig());
        valuesProperty.setDataType(valuesDataType);
        ObjectDataTypeConfig inputDataType = new ObjectDataTypeConfig();
        inputDataType.setProperties(Arrays.asList(valuesProperty));

        // 出参是一个列表
        StringDataTypeConfig labelDataType = new StringDataTypeConfig();
        ObjectProperty labelProperty = new ObjectProperty(params.getLabelField(), labelDataType);
        DataTypeConfig valueDataType = null;
        if (params.getValueDataType() == OptionsApiConfigParams.ValueDataTypeEnum.STRING) {
            StringDataTypeConfig stringDataTypeConfig = new StringDataTypeConfig();
            valueDataType = stringDataTypeConfig;
        } else {
            NumberDataTypeConfig numberDataTypeConfig = new NumberDataTypeConfig();
            valueDataType = numberDataTypeConfig;
        }
        ObjectProperty valueProperty = new ObjectProperty(params.getValueField(), valueDataType);
        ObjectDataTypeConfig optionDataType = new ObjectDataTypeConfig();
        optionDataType.setProperties(Arrays.asList(labelProperty, valueProperty));
        ArrayDataTypeConfig outputDataType = new ArrayDataTypeConfig();
        outputDataType.setItemsDataType(optionDataType);

        ApiConfig apiConfig = new ApiConfig(inputDataType, outputDataType);
        return apiConfig;
    }

}
