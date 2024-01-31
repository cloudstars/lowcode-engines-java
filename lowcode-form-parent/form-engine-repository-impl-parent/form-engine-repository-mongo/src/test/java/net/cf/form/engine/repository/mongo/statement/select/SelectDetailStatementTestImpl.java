package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.testcases.statement.select.SelectDetailExpandTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Deprecated
public class SelectDetailStatementTestImpl implements SelectDetailExpandTest {

    // 查询子表的全部字段（按对象展开）
    private static final String SELECT_DETAIL_ALL_SQL = "select TRV_TB.*, TRV_SCHE_TB.* from TRV_TB left join TRV_SCHE_TB on git TRV_TB.RECORD_ID = TRV_SCHE_TB.TRAVEL_ID;";


    @Resource
    private DataObjectResolver resolver;


    @Override
    public void testSelectTravelAndScheduleExpand1() {

    }

    @Override
    public void testSelectTravelAndScheduleExpand2() {

    }
}
