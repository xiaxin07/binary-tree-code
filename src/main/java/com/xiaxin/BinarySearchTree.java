package com.xiaxin;


import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        // 1.判空
        // 2.判断是否是第一个节点
        // 3.找到父节点 添加元素
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }

        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> parent = root;
        Node<E> node = this.root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> newNode = createNode(element, parent);
        // 插入到父节点的哪个位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;
        afterAdd(newNode);
    }

    /**
     * 添加node之后的调整
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {
    }

    /**
     * 删除node之后的调整
     *
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) {
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public void remove(E element) {
        remove(node(element));
    }

    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        // 叶子结点
        // 度为1的节点
        // 度为2的节点

        size--;
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(node);
        } else if (node.parent == null) {
            root = null;
            afterRemove(node);

        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    public Node<E> node(E element) {
        elementNotNullCheck(element);

        Node<E> node = root;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }


    public boolean contains(E element) {
        return node(element) != null;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }


}
