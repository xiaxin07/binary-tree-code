package com.linkedlist;

import com.linkedlist.circle.CircleSingleLinkedList;
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

    public static void circleSingle() {
        List<Integer> list = new CircleSingleLinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.remove(list.size() - 1);
        System.out.println(list);
    }

    public static void main(String[] args) {
        circleSingle();
    }
}
