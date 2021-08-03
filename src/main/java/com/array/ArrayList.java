package com.array;

import org.omg.CORBA.Object;

public class ArrayList<E> {
    private int size;

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = capacity > DEFAULT_CAPACITY ? capacity : DEFAULT_CAPACITY;
        elements = (E[])new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return false;
    }

    public void add(E element) {
        add(size, element);
    }

    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public void add(int index, E element) {
        rangeCheckForAnd(index);

        ensureCapacity(size + 1);
        // 扩容
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = ((E[])new Object[newCapacity]);
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    public E remove(int index) {
        rangeCheck(index);

        E oldElement = elements[index];

        // 示例
        //for (int i = index + 1; i < size; i++) {
        //    elements[i - 1] = elements[i];
        //}

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return oldElement;
    }

    int indexOf(E element) {

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        size = 0;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAnd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
