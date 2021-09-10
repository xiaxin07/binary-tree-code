package practice.heap;

import java.util.Comparator;

import practice.heap.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        testMax();
    }

    private static void testAddRemove() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] arr = new int[]{7, 21, 83, 82, 49, 55, 47, 53, 29, 43, 4, 28, 16, 88, 93, 12};
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }

        BinaryTrees.print(heap);
        System.out.println();
        heap.replace(69);
        BinaryTrees.print(heap);
        System.out.println();

        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        System.out.println("\nremove-----------------");
        BinaryTrees.print(heap);
    }

    public static void testHeapify() {
        // 大顶堆
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        //        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        //        BinaryTrees.println(heap);
        //
        //        data[0] = 10;
        //        data[1] = 20;
        //        BinaryTrees.println(heap);

        // 小顶堆
        System.out.println();
        BinaryHeap<Integer>
            heapMin = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        BinaryTrees.println(heapMin);

        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heapMin);
    }

    // 找出最大的前k个数
    public static void testMax() {

        // 新建一个小顶堆
        BinaryHeap<Integer> heapMin = new BinaryHeap<>((o1, o2) -> o2 - o1);

        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
            91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
            90, 6, 65, 49, 3, 26, 61, 21, 48};

        for (int i = 0; i < data.length; i++) {
            if (i <= k-1) {
                heapMin.add(data[i]);
            } else if (data[i] > heapMin.get()) {
                heapMin.replace(data[i]);
            }
        }

        //for (Integer datum : data) {
        //    if (heapMin.size() < k) {
        //        heapMin.add(datum);
        //    } else if (datum > heapMin.get()) {
        //        heapMin.replace(datum);
        //    }
        //}

        BinaryTrees.print(heapMin);
        System.out.println();
    }
}
