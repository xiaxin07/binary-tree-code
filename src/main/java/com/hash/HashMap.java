package com.hash;

import com.hash.printer.BinaryTreeInfo;
import com.hash.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private static final int DEFAULT_CAPACITY = 1 << 4;

    private int size;

    Node<K, V>[] table;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        // 别忘了
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {

        int index = index(key);

        Node<K, V> root = table[index];

        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);

            return null;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        boolean searched = false;
        Node<K, V> result;

        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;

            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
            } else if (searched) {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    node = result;
                    cmp = 0;
                } else {
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }


            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);

        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        fixAfterPut(newNode);

        return null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K, V> node) {

        if (node == null) {
            return null;
        }

        V oldValue = node.value;
        size--;

        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            node = s;
        }

        Node<K, V> replacement = node.left == null ? node.right : node.left;
        int index = index(node);

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node.isLeftChild()) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            fixAfterRemove(replacement);

        } else if (node.parent == null) {
            table[index] = null;
        } else {
            if (node.isLeftChild()) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

            fixAfterRemove(node);
        }

        return oldValue;
    }


    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        Node<K, V> left = node.left;
        Node<K, V> p = left;
        if (left != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        Node<K, V> s = node.right;
        if (s != null) {
            while (s.left != null) {
                s = s.left;
            }
            return s;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    @Override
    public V get(K key) {

        Node<K, V> node = node(key);

        return node == null ? null : node.value;
    }

    private Node<K, V> node(K key) {
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }
        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) {
                    return;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    public void print() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> root = table[i];
            System.out.println("【index=" + i + "】");
            BinaryTrees.print(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("\n-------------------------------------------");
        }
    }

    /**
     * 扫描
     *
     * @param node
     * @param k1
     * @return
     */
    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        Node<K, V> result;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;

            // 先比较hash值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) { // 比较key是否相等
                return node;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) { // 可比较
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) { // 扫描右子节点
                return result;
            } else { // 扫描左子节点
                node = node.left;
            }
        }
        return null;
    }

    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }


    public void fixAfterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }

        if (isBlack(parent)) {
            return;
        }

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = red(parent.parent);

        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            fixAfterPut(grand);
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

    public void fixAfterRemove(Node<K, V> node) {

        if (isRed(node)) {
            return;
        }
        Node<K, V> parent = node.parent;
        Node<K, V> sibling = node.sibling();


        boolean left = parent.left == null;

    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;

        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }


    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {

        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            table[index(grand)] = parent;
        }

        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color = color;
        return node;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {

            return "Node_" + key + "_" + value + ":" + color;
        }
    }
}
