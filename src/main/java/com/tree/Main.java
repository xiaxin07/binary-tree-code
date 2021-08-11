package com.tree;

import com.tree.BinaryTree.Visitor;
import com.tree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testRBTree();
    }

    public static void testRBTree() {
        int[] arr = {51, 59, 18, 41, 22, 3, 29, 48, 28, 39, 49, 32, 21};
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i : arr) {
            rbTree.add(i);
            BinaryTrees.print(rbTree);
            System.out.println();
        }
        System.out.println("-------------------remove--------------------");
        for (int i : arr) {
            System.out.println(i);
            rbTree.remove(i);
            BinaryTrees.print(rbTree);
            System.out.println();
        }
    }

    public static void testAvlTree() {
        int[] arr = {9, 6, 4, 8, 15, 16, 14, 12, 13};
        BinarySearchTree<Integer> avlTree = new AVLTree<>();
        for (int i : arr) {
            avlTree.add(i);
        }
        BinaryTrees.print(avlTree);
        System.out.println();
        for (int i : arr) {
            avlTree.remove(i);
            BinaryTrees.print(avlTree);
            System.out.println();
        }
    }

    public static void testBinarySearchTree() {
        int[] arr = {7, 4, 9, 2, 5, 8, 11, 1, 3, 12};

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i : arr) {
            binarySearchTree.add(i);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println();
        binarySearchTree.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer integer) {
                System.out.println(integer);
                if (integer == 8) {
                    //return true;
                }
                return false;
            }
        });
        System.out.print("\n高度1: ");
        System.out.print(binarySearchTree.height());
        System.out.print("\n高度2: ");
        System.out.print(binarySearchTree.height2());
        //System.out.print("\nreverse:\n");
        //binarySearchTree.reverse();
        System.out.println();
        BinaryTrees.print(binarySearchTree);
        System.out.println();
        for (int i : arr) {
            binarySearchTree.remove(i);
            System.out.println();
            BinaryTrees.print(binarySearchTree);
        }
    }
}
