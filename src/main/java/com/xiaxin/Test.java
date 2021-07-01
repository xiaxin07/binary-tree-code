package com.xiaxin;

import com.mj.printer.BinaryTrees;
import com.xiaxin.BinarySearchTree.Visitor;

public class Test {
    public static void main(String[] args) {
        Integer[] list = new Integer[]{17, 25, 16, 19, 40, 69, 53, 12, 10};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for (Integer e : list) {
            binarySearchTree.add(e);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println();
//        binarySearchTree.preOrderTraversal();
//        System.out.println();
//        binarySearchTree.inOrderTraversal();
//        System.out.println();
//        binarySearchTree.postOrderTraversal();
//        System.out.println();
//        binarySearchTree.levelOrderTraversal();

//

        binarySearchTree.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element + "_");
                if (element == 25) {
                    return true;
                }
                return false;
            }
        });
    }
}
