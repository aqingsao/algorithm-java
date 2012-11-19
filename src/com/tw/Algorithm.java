package com.tw;

public class Algorithm {

    public static Number logOf(int i) {
        if (i < 0) {
            return Double.NaN;
        }
        if (i == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        int ret = 0;
        while (i > 1) {
            if (i % 2 != 0) {
                throw new RuntimeException("");
            }
            i = i / 2;
            ret++;
        }
        return ret;
    }

    public static boolean isPowerOf2(int i) {
        return isPowerOf2_2(i);
    }

    private static boolean isPowerOf2_2(int i) {
        return i > 0 && (i & (i - 1)) == 0;
    }

    private static boolean isPowerOf2_1(int i) {
        while (i > 1) {
            if (i % 2 != 0) {
                return false;
            }
            i = i / 2;
        }
        return i == 1;
    }
}
