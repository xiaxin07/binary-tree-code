package practice.tree;

import practice.tree.printer.BinaryTrees;

public class Main {

    public static void testAdd() {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        int[] arr = new int[]{7, 4, 9, 2, 5, 8, 11, 3};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        binaryTree.add(12);
        binaryTree.add(1);
        System.out.println();
        BinaryTrees.print(binaryTree);
        System.out.println();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        int[] arr = new int[]{7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(9);
        BinaryTrees.print(binaryTree);
        System.out.println();
        binaryTree.remove(7);
        BinaryTrees.print(binaryTree);
        System.out.println();
        binaryTree.remove(11);
        BinaryTrees.print(binaryTree);
        System.out.println();
        binaryTree.remove(4);
        BinaryTrees.print(binaryTree);
        System.out.println();
    }
}
