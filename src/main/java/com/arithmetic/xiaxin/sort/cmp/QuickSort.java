package com.arithmetic.xiaxin.sort.cmp;

import com.arithmetic.xiaxin.sort.Sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }

        int mid = pivotIndex(begin, end);

        sort(begin, mid);
        sort(mid + 1, end);
    }

    private int pivotIndex(int begin, int end) {

        T pivot = array[begin];

        end--;

        while (begin < end) {

            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }

        array[begin] = pivot;

        return begin;
    }

}
