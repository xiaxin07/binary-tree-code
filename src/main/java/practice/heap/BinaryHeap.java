package practice.heap;

import org.omg.CORBA.Object;

import java.util.Comparator;

public class BinaryHeap<E> implements Heap<E> {

    private int size;
    private E[] elements;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < elements.length; i++) {
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

            if (cmp(element, parent) <= 0) {
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
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = ((E[]) new Object[newCapacity]);
        /*for (int i = 0; i < elements.length; i++) {
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
    public void remove() {

    }

    @Override
    public E replace(E element) {
        return null;
    }

    private int cmp(E e1, E e2) {
        return comparator == null ?
                ((Comparable<E>) e1).compareTo(e2) :
                comparator.compare(e1, e2);
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
