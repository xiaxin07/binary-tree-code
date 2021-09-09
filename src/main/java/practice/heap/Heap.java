package practice.heap;

public interface Heap<E> {

    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    E get();

    void remove();

    E replace(E element);
}
