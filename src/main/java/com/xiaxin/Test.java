package com.xiaxin;

import com.mj.printer.BinaryTrees;
import com.xiaxin.BinarySearchTree.Visitor;

public class Test {
    public static void main(String[] args) {
        Integer[] list = new Integer[]{13, 14, 15, 12, 11, 17, 16, 8, 9, 1};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (Integer e : list) {

            binarySearchTree.add(e);
            System.out.println();
            BinaryTrees.print(binarySearchTree);
        }
//        BinaryTrees.print(binarySearchTree);
        System.out.println();
//        binarySearchTree.preOrderTraversal();
//        System.out.println();
//        binarySearchTree.inOrderTraversal();
//        System.out.println();
//        binarySearchTree.postOrderTraversal();
//        System.out.println();
//        binarySearchTree.levelOrderTraversal();

//

//        binarySearchTree.levelOrder(new Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.println(element + "_");
//                if (element == 25) {
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        System.out.println();
//        binarySearchTree.predecessor(4);
        binarySearchTree.remove(11);
        BinaryTrees.print(binarySearchTree);

    }
}
