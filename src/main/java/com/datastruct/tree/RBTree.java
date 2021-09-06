package com.datastruct.tree;

import java.util.Comparator;

public class RBTree<E> extends BalanceBinarySearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // node.parent 是黑色 不用调整
        // node.parent 是红色 分几种情况：
        // 1.node.uncle是黑色
        //   node在左边
        //   node在右边
        // 2.node.uncle是红色
        Node<E> parent = node.parent;

        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) {
            black(uncle);
            black(parent);
            afterAdd(grand);
            return;
        }

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        if (isRed(node)) {
            return;
        }

        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;

        if (parent == null) {
            return;
        }
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {
            // 红
            if (isRed(sibling)) {
                red(parent);
                black(sibling);
                rotateLeft(parent);
                sibling = parent.right;
            }

            // 黑
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else {
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        } else {
            // 红
            if (isRed(sibling)) {
                red(parent);
                black(sibling);
                rotateRight(parent);
                sibling = parent.left;
            }

            // 黑
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack = isBlack(parent);
                red(sibling);
                black(parent);
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else {
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }

        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
