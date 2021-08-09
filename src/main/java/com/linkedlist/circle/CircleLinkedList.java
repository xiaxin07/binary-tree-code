package com.linkedlist.circle;

import com.linkedlist.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private Node<E> last;

    private Node<E> current;

    public void reset() {
        current = first;
    }

    public E remove() {
        if (current == null) {
            return null;
        }
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return element;
    }

    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAnd(index);

        // 为什么要分情况？ 添加在尾部不需要获取index位置的值
        // 还代表一种情况 index=0
        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) { // 添加第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index); // first
            Node<E> prev = next.prev; // last
            Node<E> node = new Node<>(prev, element, next); // newFirst

            next.prev = node;
            prev.next = node;

            if (next == first) {
                first = node;
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        return remove(node(index));
    }

    private E remove(Node<E> node) {

        if (size == 1) {
            first = null;
            last = null;
        } else {

            // 不分情况
            Node<E> prev = node.prev;
            Node<E> next = node.next;

            prev.next = next;
            next.prev = prev;

            if (node == first) {
                first = next;
            }

            if (node == last) {
                last = prev;
            }
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(prev == null ? null : prev.element);
            sb.append("_");
            sb.append(element);
            sb.append("_");
            sb.append(next == null ? null : next.element);
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("size=").append(size).append(", [");

        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
