package org.shashwatksingh.dsa.sortingalgos;

import java.util.Arrays;

import org.shashwatksingh.dsa.helpers.Swap;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        quickSort.sort(arr, 0, arr.length-1);
        System.out.println("---------------------------------------");
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("\n---------------------------------------");
    }

    /*
    The idea is that the element is chosen at random and the left part is either less than/more than pivot element.
    The right is vice-versa. 
    This is an in-place divide and conquer algorithm. Therefore, you separately sort the left part and right part.
    The complexity can be O(nlogn) to O(n^2).
    In order to reduce the complexity, you can choose the pivot at random.
    */

    public void sort(int[] arr, int low, int high) {
        if(low<high) {
            int partition = partition(arr, low, high);
            sort(arr, low, partition-1);
            sort(arr, partition+1, high);
        }
    }

    // Lomuto's partition
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        // tracking the larger elements
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                Swap.swap(arr, i, j);
            }
        }

        Swap.swap(arr, i+1, high);
        return i+1;
    }

}
