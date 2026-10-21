package org.shashwatksingh.dsa.sortingalgos;

import java.util.Arrays;

import org.shashwatksingh.dsa.helpers.GenerateArray;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        GenerateArray generateArray = new GenerateArray();
        int[] arr = generateArray.generate(15);
        System.out.println("--------Before--------");
        System.out.println(Arrays.toString(arr));

        mergeSort.sort(arr);

        System.out.println("--------After--------");
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        int len = arr.length;
        if(len < 2) return;

        int mid = len/2;

        int[] L = new int[mid];
        int[] R = new int[len-mid];

        System.arraycopy(arr, 0, L, 0, mid);
        System.arraycopy(arr, mid, R, 0, len-mid);

        sort(L);
        sort(R);

        merge(arr, L, R);
    }

    private void merge(int[] arr, int[] L, int[] R) {
        int l1 = L.length, l2 = R.length;
        int i = 0, j = 0, k = 0; 

        while (i<l1 && j<l2) {
            if(L[i]<=R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i<l1) arr[k++] = L[i++];
        while (j<l2) arr[k++] = R[j++];
    }
}
