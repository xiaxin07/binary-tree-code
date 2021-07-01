package com.xiaxin;

import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

//@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    private Node<E> root;
    private int size;

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void isEmpty() {

    }

    public void clear() {

    }

    public int size() {
        return size;
    }

    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preOrder(root, visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        visitor.stop = visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inOrder(root, visitor);
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inOrder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postOrder(root, visitor);
    }

    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) {
                break;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //public void preOrderTraversal() {
    //    preOrderTraversal(root);
    //}
    //
    //private void preOrderTraversal(Node<E> node) {
    //
    //    if (node == null) {
    //        return;
    //    }
    //    E element = node.element;
    //    System.out.println(element);
    //
    //    preOrderTraversal(node.left);
    //    preOrderTraversal(node.right);
    //}
    //
    //
    //public void inOrderTraversal() {
    //    inOrderTraversal(root);
    //}
    //
    //private void inOrderTraversal(Node<E> node) {
    //    if (node == null) {
    //        return;
    //    }
    //    inOrderTraversal(node.left);
    //    System.out.println(node.element);
    //    inOrderTraversal(node.right);
    //}
    //
    //public void postOrderTraversal() {
    //    postOrderTraversal(root);
    //}
    //
    //private void postOrderTraversal(Node<E> node) {
    //    if (node == null) {
    //        return;
    //    }
    //    postOrderTraversal(node.left);
    //    postOrderTraversal(node.right);
    //    System.out.println(node.element);
    //}
    //
    //public void levelOrderTraversal() {
    //    if (root == null) {
    //        return;
    //    }
    //    Queue<Node<E>> queue = new LinkedList<>();
    //    queue.offer(root);
    //
    //    while (!queue.isEmpty()) {
    //        Node<E> node = queue.poll();
    //        System.out.println(node.element);
    //        if (node.left != null) {
    //            queue.offer(node.left);
    //        }
    //        if (node.right != null) {
    //            queue.offer(node.right);
    //        }
    //    }
    //}

    public void add(E element) {
        // 1.判空
        // 2.判断是否是第一个节点
        // 3.找到父节点 添加元素
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }

        if (root == null) {
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        // 插入到父节点的哪个位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;
    }


    public void remove(E element) {
        remove(node(element));
    }


    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        if (node.hasTwoChildren()) {

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

    public static void main(String[] args) {

    }

    public boolean contains(E element) {
        return true;
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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }


    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }


    private static class Node<E> {
        private E element;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }


        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
