package net.cf.commons.test.db.dataset;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
            String tableName = tableJson.getString("name");

            JSONArray columnsJson = tableJson.getJSONArray("columns");
            if (columnsJson == null || columnsJson.size() == 0) {
                throw new DataSetException("数据表的列不能为空，请检查columns属性！");
            }
            int cs = columnsJson.size();
            Column[] columns = new Column[cs];
            for (int j = 0; j < cs; j++) {
                JSONObject columnJson = columnsJson.getJSONObject(j);
                String columnName = columnJson.getString("name");
                String dataType = columnJson.getString("dataType");
                if (dataType == null) {
                    dataType = DataType.CHAR.name();
                }
                Column column = new Column(columnName, DataType.valueOf(dataType.toUpperCase()));
                if (columnJson.containsKey("autoGen")) {
                    column.setAuto(columnJson.getBoolean("autoGen"));
                }
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
                    Object value;
                    if (column.isAuto()) {
                        continue;
                    }
                    if (!valuesJson.containsKey(columnName)) {
                        value = column.getDefaultValue();
                    } else {
                        value = valuesJson.get(columnName);
                    }
                    if (value == null) {
                        values.put(columnName, null);
                        continue;
                    }


                    DataType dataType = column.getDataType();
                    if (dataType == DataType.CHAR) {
                        values.put(columnName, value);
                    } else if (dataType == DataType.BOOLEAN) {
                        values.put(columnName, Boolean.parseBoolean(String.valueOf(value)));
                    } else if (dataType == DataType.INTEGER) {
                        values.put(columnName, Integer.parseInt(String.valueOf(value)));
                    } else if (dataType == DataType.DECIMAL) {
                        values.put(columnName, new BigDecimal(String.valueOf(value)));
                    } else if (dataType == DataType.DATETIME) {
                        values.put(columnName, new Date(toDateTime(String.valueOf(value))));
                    } else if (dataType == DataType.DATE) {
                        values.put(columnName, new Date(toDate(String.valueOf(value))));
                    } else if (dataType == DataType.JSON) {
                        values.put(columnName, value);
                    } else {
                        values.put(columnName, String.valueOf(value));
                    }
                }
                table.addValues(values);
            }
            dataSet.addTable(table);
        }

        return dataSet;
    }

    private static Long toDate(String value) {
        java.util.Date date = null;
        try {
            date = SDF_DATE.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return date.getTime();
    }

    private static Long toDateTime(String value) {
        java.util.Date date = null;
        try {
            date = SDF_DATETIME.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return date.getTime();
    }

}
