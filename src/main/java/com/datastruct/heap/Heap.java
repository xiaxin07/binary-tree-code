package com.datastruct.heap;

public interface Heap<E> {

    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    /**
     * 获取堆顶元素
     *
     * @return
     */
    E get();

    /**
     * 删除堆顶元素
     *
     * @return
     */
    E remove();

    /**
     * 删除堆顶元素的同时插入一个新元素
     *
     * @param element
     * @return
     */
    E replace(E element);
}
