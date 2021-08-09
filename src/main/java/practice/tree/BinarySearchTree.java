package practice.tree;

public class BinarySearchTree<E> {

    private int size;

    public void add(E element) {

    }

    public void remove() {

    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
