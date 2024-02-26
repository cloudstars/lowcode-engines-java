package net.cf.form.engine.record;

import net.cf.form.engine.RecordEngine;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
public class RecordInsertMysqlTest {

    @Resource
    private RecordEngine recordEngine;
    
}
