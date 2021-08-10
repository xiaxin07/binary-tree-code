package practice.tree;

import practice.tree.printer.BinaryTrees;

public class Main {

    public static void testAdd() {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        int[] arr = new int[] {7, 4, 9, 2, 5, 8, 11, 3};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        binaryTree.add(12);
        binaryTree.add(1);
        System.out.println();
        BinaryTrees.print(binaryTree);
        System.out.println();
    }

    public static void testRemove() {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        int[] arr = new int[] {7, 4, 9, 2, 11, 1, 3, 10, 12};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        BinaryTrees.print(binaryTree);
        System.out.println();
        System.out.println(binaryTree.height());

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
        System.out.println(binaryTree.height2());
    }

    public static void testAVLAdd() {
        BinarySearchTree<Integer> binaryTree = new AVLTree<>();
        int[] arr = new int[] {13, 14, 15, 12, 11, 17, 16, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        System.out.println();
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(13);
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(12);
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(11);
        BinaryTrees.print(binaryTree);
        System.out.println();

    }

    public static void main(String[] args) {
        testAVLAdd();
    }
}
