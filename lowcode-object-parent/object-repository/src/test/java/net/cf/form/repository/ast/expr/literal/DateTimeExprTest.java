package net.cf.form.repository.ast.expr.literal;

import com.alibaba.druid.sql.ast.expr.SQLDateExpr;
import com.alibaba.druid.sql.ast.expr.SQLDateTimeExpr;
import com.alibaba.druid.sql.ast.expr.SQLTimeExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlDateExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlTimeExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlTimestampExpr;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.TimeZone;

/**
 * 日期时间表达式解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class DateTimeExprTest {

    @Test
    public void testDateTime1() {
        String ts = "2024-02-02 02:02:02";
        SqlTimestampExpr tsExpr = new SqlTimestampExpr(ts);
        SQLDateTimeExpr druidTsExpr = new SQLDateTimeExpr(ts);
        Assert.assertTrue(tsExpr.toString().equalsIgnoreCase(druidTsExpr.toString()));
    }

    @Test
    public void testDateTime2() {
        Date ts = new Date();
        SqlTimestampExpr tsExpr = new SqlTimestampExpr(ts);
        SQLDateTimeExpr druidTsExpr = new SQLDateTimeExpr(ts, TimeZone.getDefault());
        Assert.assertTrue(tsExpr.toString().equalsIgnoreCase(druidTsExpr.toString()));
    }

    @Test
    public void testDate1() {
        String ts = "2024-02-02";
        SqlTimestampExpr dateExpr = new SqlTimestampExpr(ts);
        SQLDateTimeExpr druidDateExpr = new SQLDateTimeExpr(ts);
        Assert.assertTrue(dateExpr.toString().equalsIgnoreCase(druidDateExpr.toString()));
    }

    @Test
    public void testDate2() {
        Date ts = new Date();
        SqlDateExpr tsExpr = new SqlDateExpr(ts);
        SQLDateExpr druidTsExpr = new SQLDateExpr(ts, TimeZone.getDefault());
        Assert.assertTrue(tsExpr.toString().equalsIgnoreCase(druidTsExpr.toString()));
    }

    @Test
    public void testTime1() {
        String ts = "02:02:02";
        SqlTimeExpr timeExpr = new SqlTimeExpr(ts);
        SQLTimeExpr druidTimeExpr = new SQLTimeExpr(ts);
        Assert.assertTrue(timeExpr.toString().equalsIgnoreCase(druidTimeExpr.toString()));
    }

    @Test
    public void testTime2() {
        Date time = new Date(System.currentTimeMillis());
        SqlTimeExpr timeExpr = new SqlTimeExpr(time);
        SQLTimeExpr druidTsExpr = new SQLTimeExpr(time, TimeZone.getDefault());
        Assert.assertTrue(timeExpr.toString().equalsIgnoreCase(druidTsExpr.toString()));
    }
}
