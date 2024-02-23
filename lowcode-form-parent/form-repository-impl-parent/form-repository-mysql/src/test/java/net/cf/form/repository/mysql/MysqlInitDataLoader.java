package net.cf.form.repository.mysql;

import net.cf.form.repository.testcases.statement.InitData;
import net.cf.form.repository.testcases.statement.InitDataLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MysqlInitDataLoader implements InitDataLoader {

    private JdbcTemplate template;

    @Override
    public void load(List<InitData> initDatas) {
        this.template.batchUpdate();
    }
}
