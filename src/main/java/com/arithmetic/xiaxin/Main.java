package com.arithmetic.xiaxin;

import com.arithmetic.xiaxin.sort.Sort;
import com.arithmetic.xiaxin.sort.cmp.*;
import com.arithmetic.xiaxin.tools.Asserts;
import com.arithmetic.xiaxin.tools.Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10, 6, 7, 4, 3, 5, 9};
        testSorts(
                arr,
                new BubbleSort1(),
                new BubbleSort2(),
                new BubbleSort3(),
                new SelectionSort(),
                new HeapSort(),
                new InsertionSort1(),
                new InsertionSort2(),
                new InsertionSort3(),
                new MergeSort(),
                new QuickSort());
    }

    static void testSorts(Integer[] array, Sort... sorts) {


        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            System.out.println(Arrays.toString(newArray));
            Asserts.test(Integers.isAscOrder(newArray));
        }
//        Arrays.sort(sorts);

//        for (Sort sort : sorts) {
//            System.out.println(sort);
//        }
    }
}
