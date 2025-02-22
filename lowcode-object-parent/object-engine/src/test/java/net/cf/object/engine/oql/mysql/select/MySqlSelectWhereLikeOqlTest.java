
package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectWhereLikeRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectWhereLikeOqlTest extends AbstractSelectWhereLikeRepoTest {

    @Test
    @Override
    public void testSelectWhereLike() {
        super.testSelectWhereLike();
    }

    @Test
    @Override
    public void testSelectWhereNotLike() {
        super.testSelectWhereNotLike();
    }

}
