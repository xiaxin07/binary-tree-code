package com.arithmetic.xiaxin.sort.cmp;

import com.arithmetic.xiaxin.sort.Sort;

public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int cur = i;
            T val = array[cur];
            while (cur > 0 && cmp(val, array[cur - 1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = val;
        }
    }

}
