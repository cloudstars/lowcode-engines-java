package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.valuetype.TextValueTypeConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class FieldValidationUtilsTest {

    @Test
    public void testString() {
        TextValueTypeConfig valueType = new TextValueTypeConfig();
        valueType.setMinLength(5);
        valueType.setMaxLength(10);
        FieldValueConfig fieldValueConfig = new FieldValueConfig();
        fieldValueConfig.setName("root");
        fieldValueConfig.setValueType(valueType);

        FieldValidationUtils.validate("12345", fieldValueConfig);
        FieldValidationUtils.validate("1234567890", fieldValueConfig);

        boolean success = true;
        try {
            FieldValidationUtils.validate("1234", fieldValueConfig);
        } catch (InvalidDataException e) {
            success = false;
        } finally {
            Assert.assertEquals(false, success);
        }

        try {
            FieldValidationUtils.validate("01234567890", fieldValueConfig);
        } catch (InvalidDataException e) {
            success = false;
        } finally {
            Assert.assertEquals(false, success);
        }
    }

}
