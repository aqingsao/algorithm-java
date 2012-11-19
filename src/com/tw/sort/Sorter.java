package com.tw.sort;

import java.util.ArrayList;
import java.util.List;

public enum Sorter {
    QUICK {
        @Override
        public int[] sort(int[] values) {
            quickSort(values, 0, values.length - 1);
            return values;
        }

        private void quickSort(int[] values, int from, int to) {
            if (from < to) {
                int q = partition(values, from, to);
                quickSort(values, from, q - 1);
                quickSort(values, q + 1, to);
            }
        }

        private int partition(int[] values, int from, int to) {
            int i = from - 1;
            int x = values[to];

            for (int j = from; j < to; j++) {
                if (values[j] < x) {
                    swap(values, ++i, j);
                }
            }
            swap(values, i + 1, to);
            return i + 1;
        }


    }, BUBBLE {
        @Override
        public int[] sort(int[] values) {
            for (int i = 0; i < values.length - 1; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    if (values[j] < values[i]) {
                        swap(values, i, j);
                    }
                }
            }
            return values;
        }
    }, SIMPLE_SELECTOR {
        @Override
        public int[] sort(int[] values) {
            for (int i = 0; i < values.length - 1; i++) {
                int min = values[i];
                int minIndex = i;
                for (int j = i + 1; j < values.length; j++) {
                    if (values[j] < min) {
                        min = values[j];
                        minIndex = j;
                    }
                }
                values[minIndex] = values[i];
                values[i] = min;
            }
            return values;
        }
    }, HEAP_SELECTOR {
        @Override
        public int[] sort(int[] values) {
            buildMaxHeap(values);

            for (int i = values.length - 1; i > 0; i--) {
                swap(values, 0, i);
                maxHeapify(values, 0, i);
            }
            return values;
        }

        private void buildMaxHeap(int[] values) {
            for (int i = (values.length + 1) / 2 - 1; i >= 0; i--) {
                maxHeapify(values, i, values.length);
            }
        }

        private void maxHeapify(int[] values, int i, int heapSize) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            int max = values[i];
            int maxIndex = i;
            if (leftIndex < heapSize && values[leftIndex] > max) {
                max = values[leftIndex];
                maxIndex = leftIndex;
            }
            if (rightIndex < heapSize && values[rightIndex] > max) {
                max = values[rightIndex];
                maxIndex = rightIndex;
            }
            if (maxIndex != i) {
                values[maxIndex] = values[i];
                values[i] = max;
                maxHeapify(values, maxIndex, values.length - 1);
            }
        }
    }, SIMPLE_INSERTER {
        @Override
        public int[] sort(int[] values) {
            for (int i = 1; i < values.length; i++) {
                int j = i - 1;
                int temp = values[i];
                while (j >= 0 && values[j] > temp) {
                    values[j + 1] = values[j];
                    values[j] = temp;
                    j--;
                }
            }
            return values;
        }
    }, RADIX_SORTER {
        @Override
        public int[] sort(int[] values) {
            if (values.length == 0) {
                return values;
            }
            int max = values[0];
            for (int i = 0; i < values.length; i++) {
                if (values[i] > max) {
                    max = values[i];
                }
            }

            List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < 10; i++) {
                list.add(new ArrayList());
            }

            int offset = 1;
            while (max > 0) {
                // 按位排序
                for (int j = 0; j < values.length; j++) {
                    int value = values[j];
                    int digit = value / offset % 10;
                    ArrayList queue = list.get(digit);
                    queue.add(values[j]);
                }
                max = max / 10;
                offset *= 10;
                // 搜集结果
                int count = 0;
                for (int k = 0; k < 10; k++) {

                    while (list.get(k).size() > 0) {
                        values[count] = list.get(k).remove(0);
                        count++;
                    }
                }
            }

            return values;
        }
    };

    private static void swap(int[] values, int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public abstract int[] sort(int[] values);
}
