package net.cf.form.repository.testcases.statement;

import java.util.List;

/**
 * 初始化数据装载器
 *
 * @author clouds
 */
public interface InitDataLoader {

    /**
     * 装载初始化数据
     *
     * @param initDatas
     */
    void load(List initDatas);
}
