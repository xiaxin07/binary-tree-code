package com.xiaxin;

import com.avl.tree.BinaryTree;
import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {

    protected Node<E> root;
    protected int size;

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

    /**
     * 前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {

        if (node == null) {
            return null;
        }
        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            System.out.println(p.element);
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node != node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 后继节点
     *
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 前驱节点在右子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            System.out.println(p.element);
            return p;
        }
        // 从父节点、祖父节点中寻找后继节点
        while (node.parent != null && node != node.parent.left) {
            node = node.parent;
        }
        return node.parent;
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

    public boolean contains(E element) {
        return true;
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

    public static void main(String[] args) {

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

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
