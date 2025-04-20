package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.valuetype.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.valuetype.TextValueTypeImpl;
import io.github.cloudstars.lowcode.commons.data.valuetype.ValidationUtils;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.TextValueTypeConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class ValidationUtilsTest {

    @Test
    public void testString0() {
        TextValueTypeImpl valueType = this.getTextValueType(5, 10);
        ValidationUtils.validate("12345", valueType);
        ValidationUtils.validate("1234567890", valueType);
    }

    @Test(expected = InvalidDataException.class)
    public void testString1() {
        TextValueTypeImpl valueType = this.getTextValueType(5, 10);
        ValidationUtils.validate("1234", valueType);
    }

    @Test(expected = InvalidDataException.class)
    public void testString2() {
        TextValueTypeImpl valueType = this.getTextValueType(5, 10);
        ValidationUtils.validate("01234567890", valueType);
    }

    private TextValueTypeImpl getTextValueType(int minLength, int maxLength) {
        TextValueTypeConfig valueTypeConfig = new TextValueTypeConfig();
        valueTypeConfig.setMinLength(minLength);
        valueTypeConfig.setMaxLength(maxLength);
        return new TextValueTypeImpl(valueTypeConfig);
    }

}
