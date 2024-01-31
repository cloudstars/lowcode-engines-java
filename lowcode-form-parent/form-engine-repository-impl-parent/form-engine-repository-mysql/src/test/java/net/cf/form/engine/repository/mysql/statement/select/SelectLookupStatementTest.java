package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.testcases.statement.select.SelectLookupTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class SelectLookupStatementTest implements SelectLookupTest {

    private static final String SELECT_LOOKUP_ALL_SQL = "select TRV_CLM_TB.*, TRV_SCHE_TB.* from TRV_CLM_TB left join TRV_SCHE_TB on TRV_CLM_TB.TRV_SCHE_ID = TRV_SCHE_TB.RECORD_ID;";

    @Resource
    private DataObjectResolver resolver;


}
