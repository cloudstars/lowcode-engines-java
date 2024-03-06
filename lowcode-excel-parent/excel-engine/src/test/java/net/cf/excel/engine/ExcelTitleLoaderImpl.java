package net.cf.excel.engine;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.excel.engine.commons.ExcelTitleGroupImpl;
import net.cf.excel.engine.commons.SingleExcelTitleImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 19:04
 * @Description: ExcelTitleLoader实现类
 */
public class ExcelTitleLoaderImpl implements ExcelTitleLoader {
    @Override
    public List<ExcelTitle> loadTitles(JSONArray jsonArray) {
        Reflections reflections = new Reflections("net.cf.excel.engine.commons");
        Set<Class<? extends DataFormatter>> classes = reflections.getSubTypesOf(DataFormatter.class);
        List<DataFormatter> dataFormatters = new ArrayList<>();

        for (Class<? extends DataFormatter> clazz : classes) {
            try {
                DataFormatter dataFormatter = clazz.newInstance();
                dataFormatters.add(dataFormatter);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Map<String, DataFormatter> dataFormatterMap =
                dataFormatters.stream().collect(Collectors.toMap(DataFormatter::getDataFormatterCode, dataFormatter -> dataFormatter));

        List<ExcelTitle> titles = new ArrayList<>();
        doParse(jsonArray, dataFormatterMap, titles);

        return titles;
    }

    private void doParse(JSONArray jsonArray, Map<String, DataFormatter> dataFormatterMap, List titles) {
        jsonArray.forEach(jsonObject -> {
            JSONArray subTitles = (JSONArray) ((JSONObject) jsonObject).get("subTitles");
            String code = (String) ((JSONObject) jsonObject).get("code");
            String name = (String) ((JSONObject) jsonObject).get("name");
            String dataFormatterCode = (String) ((JSONObject) jsonObject).get("dataFormatterCode");
            if (CollectionUtils.isNotEmpty(subTitles)) {
                boolean isCollection = (boolean) ((JSONObject) jsonObject).get("isCollection");
                List<SingleExcelTitle> subTitlesList = new ArrayList<>();
                titles.add(new ExcelTitleGroupImpl(code, name, isCollection, dataFormatterMap.get(dataFormatterCode), subTitlesList));
                doParse(subTitles, dataFormatterMap, subTitlesList);
            } else {
                titles.add(new SingleExcelTitleImpl(code, name, dataFormatterMap.get(dataFormatterCode)));
            }
        });
    }
}