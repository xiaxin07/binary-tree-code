package practice.tree;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        Node<E> parent = root;
        Node<E> node = root;
        int compare = 0;
        while (node != null) {
            parent = node;
            compare = compare(element, node.element);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> newNode = new Node<>(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;
    }

    public void remove(E element) {
        if (element == null) {
            return;
        }
        remove(node(element));
    }

    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        Node<E> replacement = node.right != null ? node.right : node.left;

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }
            return;
        }

        if (node.parent == null) {
            root = null;
            return;
        }
        if (node == node.parent.right) {
            node.parent.right = null;
        } else {
            node.parent.left = null;
        }
    }


    public Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare == 0) {
                return node;
            }
            if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }


    private int compare(E element1, E element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        }
        return ((Comparable<E>) element1).compareTo(element2);
    }


}
