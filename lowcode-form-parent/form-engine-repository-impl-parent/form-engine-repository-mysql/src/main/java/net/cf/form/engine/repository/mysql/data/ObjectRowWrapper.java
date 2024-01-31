package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据对象构建行的Jdbc查询行包裹器
 *
 * @author clouds
 */
public class ObjectRowWrapper implements RowMapper<Map<String, Object>>, Serializable {

    private transient DataObject object;

    private final transient Map<String, String> columnName2NameMap = new HashMap<>();

    public ObjectRowWrapper(DataObject object) {
        this.object = object;
        for (DataField field : object.getFields()) {
            this.columnName2NameMap.put(field.getColumnName().toUpperCase(), field.getName());
        }
    }

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> resultMap = new HashMap<>();
        int columnSize = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnSize; i++) {
            String columnName = rs.getMetaData().getColumnName(i);
            String name = this.columnName2NameMap.get(columnName.toUpperCase());
            resultMap.put(name, rs.getObject(i));
        }

        return resultMap;
    }
}
