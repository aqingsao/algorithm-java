package com.tw.sort;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class SelectorTest {
    @DataPoints
    public static int[][] ARRAYS = new int[][]{new int[0], new int[]{1}, new int[]{3, 1}, new int[]{1, 3, 5 ,7}, new int[]{27, 5, 3, 1}};

    @Test
    public void test_quick_sort_array(int[] array) {
        int actual = QuickSelector.select(array, 2);

    }


    private void printIntArray(int[] actual) {
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i]);
            System.out.print("\t");
        }
        System.out.println();
    }

}
