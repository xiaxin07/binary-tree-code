package practice.tree;

import java.util.Comparator;

public class AVLTree<E> extends BinaryBalanceSearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void afterAdd(Node<E> node) {
        while (node != null) {
            if (isBalanced(node)) {
                updateHeight(node);
                node = node.parent;
            } else {
                reBalance(node);
                return;
            }
        }
    }

    @Override
    public void afterRemove(Node<E> node) {
        while (node != null) {
            if (isBalanced(node)) {
                updateHeight(node);
                node = node.parent;
            } else {
                reBalance(node);
            }
        }
    }

    @Override
    public Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {

        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void reBalance(Node<E> grand) {

        Node<E> parent = ((AVLNode<E>)grand).tallerChild();

        Node<E> child = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()) {
            if (child.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (child.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }


    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        updateHeight(grand);
        updateHeight(parent);
    }

    private static class AVLNode<E> extends Node<E> {

        int height;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }
    }
}
