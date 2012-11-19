package com.tw.sort;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class SortTest {
    @DataPoints
    public static int[][] ARRAYS = new int[][]{new int[0], new int[]{1}, new int[]{3, 1}, new int[]{1, 3, 5 ,7}, new int[]{27, 5, 3, 1}};

    @Theory
    public void test_bubble_sort_array(int[] array) {
        int[] actual = Sorter.BUBBLE.sort(array);
        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    @Theory
    public void test_quick_sort_array(int[] array) {
        int[] actual = Sorter.QUICK.sort(array);
        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    @Theory
    public void test_quick_selector_sort_array(int[] array) {
        int[] actual = Sorter.SIMPLE_SELECTOR.sort(array);
        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    @Theory
    public void test_heap_selector_sort_array(int[] array) {
        int[] actual = Sorter.HEAP_SELECTOR.sort(array);
        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    @Theory
    public void test_simple_inserter_sort_array(int[] array) {
        int[] actual = Sorter.SIMPLE_INSERTER.sort(array);

        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    @Theory
    public void test_radix_sort_array(int[] array) {
        int[] actual = Sorter.RADIX_SORTER.sort(array);
        printIntArray(actual);

        assertThat(actual.length, is(array.length));
        for (int i = 1; i < actual.length; i++) {
            assertTrue("", actual[i - 1] <= actual[i]);
        }
    }

    private void printIntArray(int[] actual) {
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i]);
            System.out.print("\t");
        }
        System.out.println();
    }

}
