package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.mongo.statement.ObjectContextHolder;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelect;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(JUnit4.class)
@Ignore
@Deprecated
public class SelectSimpleItemsTest {

    // 查询单列
    private static final String SELECT_SINGLE_FIELD = "select f1 from objectName";

    // 查询多列
    private static final String SELECT_MULTI_ALIAS = "select f1 as a1, f2 as a2 from objectName as aName;";

    // 查询字段名是数字开头的列
    private static final String SELECT_NUMBER_START_FIELDS = "select 1a, d2, 2d, 2dd from objectName";

    // 查询常量
    private static final String SELECT_LITERAL_FIELDS = "select 1, -1.1 as aa, '2' as b from objectName";

    // 查询方法
    private static final String SELECT_METHOD_FIELD = "select now() as t from objectName";

    @Resource
    private DataObjectResolver resolver;

    @Test
    public void testSelectSingleField() {
        ObjectContextHolder.init();
        OqlSelect select = new OqlSelect();
        select.setSelectItems(Arrays.asList(new OqlSelectItem(new OqlIdentifierExpr("f1"))));
        select.setFrom(new OqlExprObjectSource("objectName"));
        OqlSelectStatement statement = new OqlSelectStatement(select);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(resolver);
        statement.accept(visitor);
        MongoSelectCommand mongoSelectCommand = visitor.getMongoAggregation();
        System.out.println(mongoSelectCommand.getMongoOql());
        ObjectContextHolder.remove();
    }

    @Test
    public void testSelectMultiFieldsAlias() {

    }

    @Test
    public void testSelectNumberStartFields() {

    }


    @Test
    public void testSelectLiteralFields() {

    }

    @Test
    public void testSelectMethodField() {

    }


}