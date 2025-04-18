package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.value.TextValueTypeConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class DataValidationUtilsTest {

    @Test
    public void testString() {
        TextValueTypeConfig config = new TextValueTypeConfig();
        config.setMinLength(5);
        config.setMaxLength(10);

        DataValidationUtils.validate("12345", config);
        DataValidationUtils.validate("1234567890", config);

        boolean success = true;
        try {
            DataValidationUtils.validate("1234", config);
        } catch (InvalidDataException e) {
            success = false;
        } finally {
            Assert.assertEquals(false, success);
        }

        try {
            DataValidationUtils.validate("01234567890", config);
        } catch (InvalidDataException e) {
            success = false;
        } finally {
            Assert.assertEquals(false, success);
        }
    }

}
