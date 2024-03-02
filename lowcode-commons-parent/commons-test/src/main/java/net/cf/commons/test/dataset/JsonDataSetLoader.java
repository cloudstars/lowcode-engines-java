package net.cf.commons.test.dataset;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON数据集加载器
 *
 * @author clouds
 */
public final class JsonDataSetLoader {

    private JsonDataSetLoader() {
    }

    /**
     * 从类路径加载JSON数据集
     *
     * @param tableFilePaths 文件所在的完整路径，如：abc/x.json, eft/y.json
     * @return
     */
    public static DefaultDataSet loadFromClassPath(String[] tableFilePaths) {
        DefaultDataSet dataSet = new DefaultDataSet();
        for (int i = 0, l = tableFilePaths.length; i < l; i++) {
            String filePath = tableFilePaths[i];
            String content = FileTestUtils.loadTextFromClasspath(filePath);
            JSONObject tableJson = JSONObject.parseObject(content);
            String tableName = tableJson.getString("code");

            JSONArray columnsJson = tableJson.getJSONArray("columns");
            if (columnsJson == null || columnsJson.size() == 0) {
                throw new DataSetException("数据表的列不能为空，请检查columns属性！");
            }
            int cs = columnsJson.size();
            Column[] columns = new Column[cs];
            for (int j = 0; j < cs; j++) {
                JSONObject columnJson = columnsJson.getJSONObject(j);
                String columnName = columnJson.getString("code");
                String dataType = columnJson.getString("dataType");
                if (dataType != null) {
                    dataType = DataType.STRING.name();
                }
                Column column = new Column(columnName, DataType.valueOf(dataType.toUpperCase()));
                columns[j] = column;
            }

            DefaultTableMetaData tableMetaData = new DefaultTableMetaData(tableName, columns);
            DefaultTable table = new DefaultTable(tableMetaData);
            JSONArray valuesListJson = tableJson.getJSONArray("valuesList");
            int vls = valuesListJson.size();
            for (int k = 0; k < vls; k++) {
                // 依次读取每一条数据，并根据数据类型作转换
                JSONObject valuesJson = valuesListJson.getJSONObject(k);
                Map<String, Object> values = new HashMap<>();
                for (int t = 0; t < cs; t++) {
                    Column column = columns[t];
                    String columnName = column.getColumnName();
                    String value;
                    if (!valuesJson.containsKey(columnName)) {
                        value = column.getDefaultValue();
                    } else {
                        value = valuesJson.getString(columnName);
                    }

                    DataType dataType = column.getDataType();
                    if (dataType == DataType.STRING) {
                        values.put(columnName, value);
                    } else if (dataType == DataType.BOOLEAN) {
                        values.put(columnName, Boolean.parseBoolean(value));
                    } else if (dataType == DataType.INTEGER) {
                        values.put(columnName, Integer.parseInt(value));
                    } else if (dataType == DataType.DECIMAL) {
                        values.put(columnName, new BigDecimal(value));
                    } else if (dataType == DataType.TIMESTAMP) {
                        values.put(columnName, new Timestamp(Long.parseLong(value)));
                    } else if (dataType == DataType.DATE) {
                        values.put(columnName, new Date(Long.parseLong(value)));
                    } else if (dataType == DataType.TIME) {
                        values.put(columnName, new Time(Long.parseLong(value)));
                    } else {
                        values.put(columnName, value);
                    }
                }
                table.addValues(values);
            }
            dataSet.addTable(table);
        }

        return dataSet;
    }
}
