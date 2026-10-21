package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
74. Search a 2D Matrix

You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104

*/

public class Search2DMatrix {
    /*
    Thinking out loud
    1. convert to flatmap and search using streams.
    2. do a binarySearch of the m*n array itself considering it to be a flat array
    */

    public static void main(String[] args) {
        Search2DMatrix search2dMatrix = new Search2DMatrix();
        System.out.println(search2dMatrix.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 12));
        System.out.println(search2dMatrix.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
    }

    public boolean searchMatrixStreams(int[][] matrix, int target) {
        int[] arr = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
        return Arrays.binarySearch(arr, target)>=0;
    }

    public boolean searchMatrixStreamsUsingBinarySearchImplementation(int[][] matrix, int target) {
        int[] arr = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
        BinarySearch binarySearch = new BinarySearch();
        return binarySearch.search(arr, target)>=0;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        if(m==0) return false;

        int left = 0, right = m*n-1;
        while (left<=right) {
            int mid = left+(right-left)/2;
            int row = mid/n, col = mid%n;
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col]>target) right= mid-1;
            else left = mid+1;
        }

        return false;
    }
}
