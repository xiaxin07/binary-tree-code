package com.arithmetic.xiaxin.sort.cmp;

import com.arithmetic.xiaxin.sort.Sort;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    private int heapSize;

    @Override
    protected void sort() {

        // 原地建堆
        heapSize = array.length;

        for (int index = (heapSize >> 1) - 1; index > 0; index--) {
            siftDown(index);
        }

        while (heapSize > 1) {
            swap(0, --heapSize);

            siftDown(0);
        }
    }

    private void siftDown(int index) {
        T element = array[index];

        int half = heapSize >> 1;
        while (index < half) { // index必须是非叶子节点
            // 默认是左边跟父节点比
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmp(element, child) >= 0) { break; }

            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }

}
