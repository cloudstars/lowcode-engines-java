package net.cf.excel.engine.bean;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-01 09:29
 * @Description: 解析excel表头的返回值
 */
public class ExcelTitleBean {
    private String name;

    private List<ExcelTitleBean> subTitles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExcelTitleBean> getSubTitles() {
        return subTitles;
    }

    public void setSubTitles(List<ExcelTitleBean> subTitles) {
        this.subTitles = subTitles;
    }
}