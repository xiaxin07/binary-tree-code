package practice.tree;

public class RBTree<E> extends BinaryBalanceSearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    @Override
    public void afterAdd(Node<E> node) {

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

        if (isRed(uncle)) { // 上溢
            black(parent);
            black(uncle);
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
    public void afterRemove(Node<E> node) {
        // 1. 删掉的节点是红色节点                  return
        // 2. 删掉的是根节点                       return
        // 3. 删掉的是带有一个红色节点的黑色节点      black(replacement)
        // 4. 删掉的是黑色节点      以right节点为例：4.1.isRed(sibling) black(sibling)、red(parent); rotateRight(parent); sibling=parent.left
        //                                       4.2.isBlack(sibling)
        //                                         isBlack(sibling.left) && isBlack(sibling.right) -> black(parent)、red(sibling); isBlack(parent)->afterRemove(parent, null)
        //                                         isRed(sibling.left) || isRed(sibling.right)
        //                                              isBlack(sibling.left)->rotateLeft(sibling); sibling=parent.left
        //                                              isRed(sibling.left)->color(sibling, colorOf(parent))、black(parent)、black(sibling.left); rotateRight()

        // 1、删掉的节点是红色节点 用以被取代的节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        // 2
        Node<E> parent = node.parent;
        if (parent == null) {
            return;
        }

        // 4
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            // 反过来
            // 兄弟节点是红色
            if (isRed(sibling)) {
                red(parent);
                black(sibling);
                rotateLeft(parent);
                sibling = parent.right;
            }

            // 兄弟节点是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有红色节点可以借
                // 下溢
                boolean parentBlack = isBlack(parent);

                red(sibling);
                black(parent);

                if (parentBlack) {
                    afterRemove(parent);
                }

            } else {
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);
            }
        } else {
            // 4.1
            // 兄弟节点是红色
            if (isRed(sibling)) {
                red(parent);
                black(sibling);
                rotateRight(parent);
                sibling = parent.left;
            }

            // 兄弟节点是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有红色节点可以借
                // 下溢
                boolean parentBlack = isBlack(parent);

                red(sibling);
                black(parent);

                if (parentBlack) {
                    afterRemove(parent);
                }

            } else {
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.left);
                rotateRight(parent);
            }
        }

    }
//    public void afterRemove(Node<E> node, Node<E> replacement) {
//        // 1. 删掉的节点是红色节点                  return
//        // 2. 删掉的是根节点                       return
//        // 3. 删掉的是带有一个红色节点的黑色节点      black(replacement)
//        // 4. 删掉的是黑色节点      以right节点为例：4.1.isRed(sibling) black(sibling)、red(parent); rotateRight(parent); sibling=parent.left
//        //                                       4.2.isBlack(sibling)
//        //                                         isBlack(sibling.left) && isBlack(sibling.right) -> black(parent)、red(sibling); isBlack(parent)->afterRemove(parent, null)
//        //                                         isRed(sibling.left) || isRed(sibling.right)
//        //                                              isBlack(sibling.left)->rotateLeft(sibling); sibling=parent.left
//        //                                              isRed(sibling.left)->color(sibling, colorOf(parent))、black(parent)、black(sibling.left); rotateRight()
//
//        // 1
//        if (isRed(node)) {
//            return;
//        }
//
//        // 3
//        if (isRed(replacement)) {
//            black(replacement);
//            return;
//        }
//
//        // 2
//        Node<E> parent = node.parent;
//        if (parent == null) {
//            return;
//        }
//
//        // 4
//        boolean left = parent.left == null || node.isLeftChild();
//        Node<E> sibling = left ? parent.right : parent.left;
//        if (left) {
//            // 反过来
//            // 兄弟节点是红色
//            if (isRed(sibling)) {
//                red(parent);
//                black(sibling);
//                rotateLeft(parent);
//                sibling = parent.right;
//            }
//
//            // 兄弟节点是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有红色节点可以借
//                // 下溢
//                boolean parentBlack = isBlack(parent);
//
//                red(sibling);
//                black(parent);
//
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            } else {
//                if (isBlack(sibling.right)) {
//                    rotateRight(sibling);
//                    sibling = parent.right;
//                }
//
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.right);
//                rotateLeft(parent);
//            }
//        } else {
//            // 4.1
//            // 兄弟节点是红色
//            if (isRed(sibling)) {
//                red(parent);
//                black(sibling);
//                rotateRight(parent);
//                sibling = parent.left;
//            }
//
//            // 兄弟节点是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) { // 兄弟节点没有红色节点可以借
//                // 下溢
//                boolean parentBlack = isBlack(parent);
//
//                red(sibling);
//                black(parent);
//
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            } else {
//                if (isBlack(sibling.left)) {
//                    rotateLeft(sibling);
//                    sibling = parent.left;
//                }
//
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.left);
//                rotateRight(parent);
//            }
//        }
//
//    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node != null) {
            ((RBNode<E>) node).color = color;
        }
        return node;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    @Override
    public Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {

        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (color == RED) {
                sb.append("R_");
            }
            sb.append(element);
            return sb.toString();
        }
    }
}
