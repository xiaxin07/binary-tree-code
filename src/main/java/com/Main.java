package com;

import com.BinaryTree.Visitor;
import com.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
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
