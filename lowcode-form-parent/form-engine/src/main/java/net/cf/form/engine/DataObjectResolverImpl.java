package net.cf.form.engine;

import net.cf.form.engine.field.XField;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据业务对象加载数据对象的解析器
 *
 * @author clouds
 */
@Deprecated
public class DataObjectResolverImpl implements DataObjectResolver {

    private XObjectResolver resolver;

    public DataObjectResolverImpl(XObjectResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public DataObject resolveObject(String objectName) {
        XObject object = resolver.resolveObject(objectName);
        if (object != null) {
            DataObject dataObject = new DataObject(object.getName(), object.getTableName());
            List<DataField> dataFields = new ArrayList<>();
            for (XField field : object.getFields()) {
                //DataField dataField = new DataField(field.getName(), field.getColumnName(), field.getDataTypeBak());
                //dataFields.add(dataField);
            }
            dataObject.addFields(dataFields);
            return dataObject;
        }


        return null;
    }
}
