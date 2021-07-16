package com;

import java.util.Comparator;

public class BalanceBinarySearchTree<E> extends BinarySearchTree<E> {
    public BalanceBinarySearchTree() {
        this(null);
    }

    public BalanceBinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

}

