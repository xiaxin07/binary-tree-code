package practice.heap;

import java.util.Comparator;

public abstract class AbstractHeap<E> implements Heap<E> {
    protected int size;
    protected Comparator<E> comparator;

    public AbstractHeap() {
    }

    public AbstractHeap(Comparator<E> comparator) {
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

    protected int compare(E e1, E e2) {
        return comparator == null ?
            ((Comparable<E>)e1).compareTo(e2) :
            comparator.compare(e1, e2);
    }

    protected void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
