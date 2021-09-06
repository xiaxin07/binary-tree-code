package com.datastruct.arraylist;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(arrayList.remove(0));
        }
        System.out.println(arrayList);
    }
}
