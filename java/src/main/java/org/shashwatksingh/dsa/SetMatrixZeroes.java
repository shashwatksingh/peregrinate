package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
73. Set Matrix Zeroes

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 

Follow up:
A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

*/

public class SetMatrixZeroes {

    public static void main(String[] args) {
        SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();
        int[][] matrix = new int[][]{{1, 0, 3}, {4, 5, 0}, {7, 8, 9}};
        setMatrixZeroes.setZeroesMarking(matrix);
        Arrays.stream(matrix)
        .forEach(ele -> Arrays.stream(ele).forEach(System.out::println));
    }

    public void setZeroesSetMarkers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        if(row.size() == 0 && col.size() == 0) return;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(row.contains(i) || col.contains(j)) matrix[i][j] = 0;
            }
        }
    }

        public void setZeroesSetMarkersOptimised(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // The problem with list is that it is O(n) for searching. 
        // Hence, set will be better for this purpose because average case, it is O(1).
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        if(row.size() == 0 && col.size() == 0) return;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(row.contains(i) || col.contains(j)) matrix[i][j] = 0;
            }
        }
    }

    public void setZeroesMarking(int[][] matrix) {
        //Use the first row, col as marker for the array

        int m = matrix.length;
        int n = matrix[0].length;
        boolean isCol = false;

        for (int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) isCol = true;

            for (int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                } 
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if(isCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
