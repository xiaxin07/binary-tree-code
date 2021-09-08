package com.arithmetic.xiaxin.sort;

public abstract class Sort<T extends Comparable<T>> {

    protected T[] array;

    public void sort(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;

        sort();
    }

    protected abstract void sort();

    protected int cmp(int i1, int i2) {
        return array[i1].compareTo(array[i2]);
    }

    protected int cmp(T v1, T v2) {
        return v1.compareTo(v2);
    }

    protected void swap(int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
