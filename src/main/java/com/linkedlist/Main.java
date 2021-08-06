package com.linkedlist;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list);

        for (int i = 0; i < 20; i++) {
            list.remove(0);
        }
        System.out.println(list);
    }
}
