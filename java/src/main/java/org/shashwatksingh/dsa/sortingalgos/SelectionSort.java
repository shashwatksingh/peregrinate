package org.shashwatksingh.dsa.sortingalgos;

import java.util.Arrays;

import org.shashwatksingh.dsa.helpers.Swap;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        selectionSort.sort(arr);
        System.out.println("---------------------------------------");
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("\n---------------------------------------");
    }

    /*

    The algo entrust us to break the array into sorted and unsorted regions.
    It is unstable sorting algorithm
    We need to find the element from the unsorted region.
    The selection sort required us to find the position of the current element in the array.
    We run an outer loop to keep track of the elements.
    The inner loop keeps track where the position of the current element will be.
    Swap the number with the current position number.
    */

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]) minIndex = j;
            }

            Swap.swap(arr, i, minIndex);
        }
    }

}
