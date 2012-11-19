package com.tw;

import org.junit.Test;

import javax.naming.NameNotFoundException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AlgorithmTest {
    @Test
    public void test_return_0_for_1() {
        int actual = (Integer) Algorithm.logOf(1);
        assertThat(actual, is(0));

    }

    @Test
    public void test_return_1_for_2() {
        int actual = (Integer) Algorithm.logOf(2);
        assertThat(actual, is(1));

    }

    @Test
    public void test_return_2_for_4() {
        int actual = (Integer) Algorithm.logOf(4);
        assertThat(actual, is(2));

    }

    @Test
    public void test_return_3_for_8() {
        int actual = (Integer) Algorithm.logOf(8);
        assertThat(actual, is(3));

    }

    @Test
    public void should_return_NaN_when_given_negative() {
        Number actual = Algorithm.logOf(-1);
        assertThat((Double) actual, is(Double.NaN));
    }

    @Test
    public void should_return_negative_infinity_when_given_0() {
        Number actual = Algorithm.logOf(0);
        assertThat((Double) actual, is(Double.NEGATIVE_INFINITY));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_exception_when_given_3() {
        Algorithm.logOf(3);
    }

    @Test
    public void should_return_31_when_given_2_powerof_31() {
        int value = (int) Math.pow(2, 30);
        System.out.println(value);
        int actual = (Integer) Algorithm.logOf(value);
        assertThat(actual, is(30));
    }

    @Test
    public void test_return_false_when_given_0(){
        assertThat(Algorithm.isPowerOf2(0), is(false));
    }

    @Test
    public void test_return_true_when_given_1(){
        assertThat(Algorithm.isPowerOf2(1), is(true));
    }

    @Test
    public void test_return_true_when_given_2(){
        assertThat(Algorithm.isPowerOf2(2), is(true));
    }

    @Test
    public void test_return_true_when_given_1024(){
        assertThat(Algorithm.isPowerOf2(1024), is(true));
    }

    @Test
    public void test_return_false_when_given_1025(){
        assertThat(Algorithm.isPowerOf2(1024), is(true));
    }
}
