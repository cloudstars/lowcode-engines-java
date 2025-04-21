package io.github.cloudstars.lowcode.commons.test.util;

import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringTestUtilsTest {

    @Test
    public void testEqualsIgnoreWhiteSpace0() {
        String s1 = " acb\t*** ";
        String s2 = "   acb***\t   ";
        StringTestUtils.assertEqualsIgnoreWhiteSpace(s1, s2);
    }

    @Test(expected = ComparisonFailure.class)
    public void testEqualsIgnoreWhiteSpace1() {
        String s1 = " acb\t**** ";
        String s2 = "   acb***\t   ";
        StringTestUtils.assertEqualsIgnoreWhiteSpace(s1, s2);
    }

}
