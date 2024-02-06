package HomeTask_240206;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static HomeTask_240206.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getSumTest() {
        int actual = getSum(8, 12);
        int expected = 20;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getSubTest() {
        int actual = getSub(12, 8);
        int expected = 4;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMultWithoutZeroTest() {
        int actual = getMult(12, 8);
        int expected = 96;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMultWithZeroTest() {
        int actual = getMult(0, 8);
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDivWithoutZeroTest() {
        int actual = getDiv(15, 3);
        int expected = 5;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDivWithZeroTest() {
        Assertions.assertThrows(ArithmeticException.class, () -> getDiv(15, 0));
    }

    @Test
    void getPowerWithoutZeroTest() {
        Assertions.assertEquals(8, getPower(2, 3));
    }

    @Test
    void getPowerWithZeroTest() {
        Assertions.assertEquals(1, getPower(2, 0));
    }

    @Test
    void getFactorialTest() {
        Assertions.assertEquals(24, getFactorial(4));
    }

    @Test
    void getFactorialWithZeroAndOneParamTest() {
        Assertions.assertEquals(1, getFactorial(0));
        Assertions.assertEquals(1, getFactorial(1));
    }

    @Test
    void getFactorialWithNegativeParamTest() {
        Assertions.assertThrows(ArithmeticException.class, () -> getFactorial(-6));
    }

    @Test
    void getAbsoluteTest() {
        Assertions.assertEquals(10, getAbsolute(-10));
        Assertions.assertEquals(10, getAbsolute(10));
    }

    @Test
    void sqrtTest() {
        Assertions.assertEquals(4, sqrt(16));
    }

    @Test
    void sqrtWithZeroAvdOneTest() {
        Assertions.assertEquals(1, sqrt(1));
        Assertions.assertEquals(0, sqrt(0));
    }

    @Test
    void logarithmTest() {
        Assertions.assertEquals(4, logarithm(2, 16));
    }




    @Test
    void sinTest() {
        Assertions.assertEquals(0.8414, sin(1.0), 0.0001);
    }
}