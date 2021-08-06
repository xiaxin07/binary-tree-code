package com.linkedlist;

import com.linkedlist.single.SingleLinkedList;

public class Main {

    public static void single() {
        List<Integer> list = new SingleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list);

        for (int i = 0; i < 20; i++) {
            list.remove(0);
        }
        System.out.println(list);
    }

    public static void bi() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list);

        for (int i = 20; i > 0; i--) {
            list.remove(0);
            System.out.println("[" + i + "]");
            System.out.println(list);
        }
        //System.out.println(list);
    }

    public static void main(String[] args) {
        bi();
    }
}
