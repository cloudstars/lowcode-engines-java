package net.cf.form.engine;

import net.cf.form.engine.field.XField;
import net.cf.form.engine.object.XObject;

import java.util.List;
import java.util.Map;

/**
 * 对象数据处理器实现类
 *
 * @author clouds
 */
@Deprecated
public class ObjectRecordProcessorImpl implements ObjectRecordProcessor {

    private ObjectRecordProcessFeature[] features;

    public ObjectRecordProcessorImpl(ObjectRecordProcessFeature[] features) {
        this.features = features;
    }

    @Override
    public void process(XObject object, Map<String, Object> dataMap) {
        List<XField> fields = object.getFields();
        for (XField field : fields) {
            processField(field, dataMap);
        }
    }

    private void processField(XField field, Map<String, Object> dataMap) {
        /*XFieldType fieldType = field.getFieldType();
        if (contains(features, ObjectRecordProcessFeature.Validate)) {
            fieldType.validator(dataMap);
        }

        if (contains(features, ObjectRecordProcessFeature.GetValue)) {
            fieldType.getValue(dataMap);
        }

        if (contains(features, ObjectRecordProcessFeature.Format)) {
            fieldType.format(dataMap);
        }

        if (contains(features, ObjectRecordProcessFeature.Unformat)) {
            fieldType.unformat(dataMap);
        }*/
    }

    @Override
    public ObjectRecordProcessFeature[] getFeatures() {
        return this.features;
    }

    @Override
    public void setFeatures(ObjectRecordProcessFeature[] features) {
        this.features = features;
    }

    boolean contains(ObjectRecordProcessFeature[] features, ObjectRecordProcessFeature target) {
        for (int i = 0, l = features.length; i < l; i++) {
            if (features[i] == target) {
                return true;
            }
        }

        return false;
    }
}
