package org.shashwatksingh.dsa.sortingalgos;

import java.util.Arrays;

import org.shashwatksingh.dsa.helpers.Swap;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort.sort(arr);
        System.out.println("---------------------------------------");
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("\n---------------------------------------");
    }

    /*
    The idea of bubble sort is to compare the current element with the next element.
    Therefore, the current element bubbles up to the position it should be in.
    We make n-1 pass over the array.
    The order is O(n^2)
    */

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len-1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < len-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    Swap.swap(arr, j, j+1);
                    isSwapped = true;
                }
            }
            if(!isSwapped) break;
        }
    }

}
