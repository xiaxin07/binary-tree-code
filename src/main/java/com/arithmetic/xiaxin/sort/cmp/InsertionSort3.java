package com.arithmetic.xiaxin.sort.cmp;

import com.arithmetic.xiaxin.sort.Sort;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin, search(begin));
        }
    }

    public void insert(int source, int dest) {

        T v = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = v;

    }

    /**
     * 二分查找找到index元素位置的待插入位置
     * 已经排好序的数组的区间范围是【0，index】
     *
     * @param index
     * @return
     */
    public int search(int index) {
        int begin = 0;
        int end = index;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 16));
        System.out.println(65536.0 / 1024);
    }
    /**
     * 二分查找
     *
     * @param v
     * @return
     */
//    public int search(T v) {
//
//        if (array == null || array.length == 0) {
//            return -1;
//        }
//        int begin = 0;
//        int end = array.length;
//
//        while (begin < end) {
//            int mid = (begin + end) >> 1;
//            T middleV = array[mid];
//            if (cmp(v, middleV) < 0) {
//                end = mid;
//            } else if (cmp(v, middleV) > 0) {
//                begin = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }
}
