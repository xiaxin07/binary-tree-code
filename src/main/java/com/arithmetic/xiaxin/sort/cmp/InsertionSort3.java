package com.arithmetic.xiaxin.sort.cmp;

import com.arithmetic.xiaxin.sort.Sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            T v = array[cur];
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }

            array[cur] = v;
        }
    }
}
