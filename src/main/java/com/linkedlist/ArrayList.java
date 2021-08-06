package com.linkedlist;

public class ArrayList<E> extends AbstractList<E> {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = capacity > DEFAULT_CAPACITY ? capacity : DEFAULT_CAPACITY;
        elements = (E[])new Object[capacity];
    }

    public void clear() {
        // 对象数组需要清除
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        // 一般size=0
        size = 0;
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

        trim();
        return oldElement;
    }

    /**
     * 缩容
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;

        if (size > newCapacity || oldCapacity <= DEFAULT_CAPACITY) {
            return;
        }
        E[] newElements = ((E[])new Object[newCapacity]);
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }


    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return ELEMENT_NOT_FOUND;
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
