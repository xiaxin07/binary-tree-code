package com.datastruct.heap;

import com.datastruct.heap.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(E[] elements) {

        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = ((E[]) new Object[DEFAULT_CAPACITY]);
            return;
        }

        size = elements.length;
        int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
        this.elements = ((E[]) new Object[capacity]);
        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = elements[i];
        }
        heapify();
    }

    private void heapify() {
//        // 自上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }
//
        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }


    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) {
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        /*for (int i = 0; i < oldCapacity; i++) {
            newElements[i] = elements[i];
        }*/
        System.arraycopy(elements, 0, newElements, 0, oldCapacity);
        elements = newElements;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        int lastIndex = --size;

        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;

        while (index < half) {

            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < size && compare(child, elements[rightIndex]) <= 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) {
                break;
            }

            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = (((int) node) << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = (((int) node) << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[((int) node)];
    }
}
