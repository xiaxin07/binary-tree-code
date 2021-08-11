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

    public static void testAVL() {
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

    public static void testRB() {
        BinarySearchTree<Integer> binaryTree = new RBTree<>();
        int[] arr = new int[] {36, 70, 64, 8, 83, 49, 100, 93, 54, 21, 87, 76, 85, 4, 19, 72, 86};
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(arr[i]);
        }
        System.out.println();
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(19);
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(21);
        BinaryTrees.print(binaryTree);
        System.out.println();

        binaryTree.remove(100);
        BinaryTrees.print(binaryTree);
        System.out.println();

    }


    public static void main(String[] args) {
        testRB();
    }
}
