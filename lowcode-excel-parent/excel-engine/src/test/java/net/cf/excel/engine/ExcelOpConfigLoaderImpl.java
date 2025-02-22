package net.cf.excel.engine;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.excel.engine.bean.ExcelBuildConfig;
import net.cf.excel.engine.bean.ExcelParseConfig;
import net.cf.excel.engine.commons.ExcelOpException;
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
 * @Description: ExcelOpConfigLoader实现类
 */
public class ExcelOpConfigLoaderImpl implements ExcelOpConfigLoader {
    @Override
    public ExcelParseConfig loadExcelParseConfig(JSONObject jsonObject) {
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
        ExcelParseConfig excelParseConfig = new ExcelParseConfig();
        try {
            doParse(jsonObject.getJSONArray("excelTitles"), dataFormatterMap, titles);
            excelParseConfig.setExcelTitles(titles);
            excelParseConfig.setTitleStartRow(jsonObject.getInteger("titleStartRow"));
            excelParseConfig.setTitleEndRow(jsonObject.getInteger("titleEndRow"));
        } catch (Exception e) {
            throw new ExcelOpException("配置文件格式错误!");
        }

        return excelParseConfig;
    }

    @Override
    public ExcelBuildConfig loadExcelBuildConfig(JSONObject jsonObject) {
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
        ExcelBuildConfig excelBuildConfig = new ExcelBuildConfig();
        try {
            doParse(jsonObject.getJSONArray("excelTitles"), dataFormatterMap, titles);
            excelBuildConfig.setExcelTitles(titles);
            excelBuildConfig.setTitleStartRow(jsonObject.getInteger("titleStartRow"));
            excelBuildConfig.setSheetName(jsonObject.getString("sheetName"));
        } catch (Exception e) {
            throw new ExcelOpException("配置文件格式错误!");
        }

        return excelBuildConfig;
    }

    private void doParse(JSONArray jsonArray, Map<String, DataFormatter> dataFormatterMap, List titles) {
        jsonArray.forEach(jsonObject -> {
            JSONArray subTitles = ((JSONObject) jsonObject).getJSONArray("subTitles");
            String code = ((JSONObject) jsonObject).getString("code");
            String name = ((JSONObject) jsonObject).getString("name");
            JSONObject dataFormatter = ((JSONObject) jsonObject).getJSONObject("dataFormatter");
            String dataFormatterCode = dataFormatter == null ? null : dataFormatter.getString("dataFormatterCode");
            if (CollectionUtils.isNotEmpty(subTitles)) {
                boolean collection = ((JSONObject) jsonObject).getBoolean("collection");
                List<SingleExcelTitle> subTitlesList = new ArrayList<>();
                titles.add(new ExcelTitleGroupImpl(code, name, collection, dataFormatterMap.get(dataFormatterCode), subTitlesList));
                doParse(subTitles, dataFormatterMap, subTitlesList);
            } else {
                titles.add(new SingleExcelTitleImpl(code, name, dataFormatterMap.get(dataFormatterCode)));
            }
        });
    }
}