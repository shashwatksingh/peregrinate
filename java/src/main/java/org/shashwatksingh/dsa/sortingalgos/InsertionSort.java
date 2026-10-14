package org.shashwatksingh.dsa.sortingalgos;

import java.util.Arrays;

public class InsertionSort {

    /*
    
    The idea is to insert the element at its right position. 
    It's a stable sorting algorithm
    Start with the second element.
    Compare the second element with the all the previous element and keep shifting the elements to right.
    And replace the element where the above element is less than the element
    Do above steps for n passes to get the sorted array.
    */

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        insertionSort.sort(arr);
        System.out.println("---------------------------------------");
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("\n---------------------------------------");
    }

    public void sort(int[] arr) {
        int len = arr.length;
        
        for (int i = 1; i < len; i++) {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j]>key) {
                arr[j+1] = arr[j--];
            }
            arr[j+1] = key;
        }
    }

}
