package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
* You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*
Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
*
* */
public class RotateImage {

    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        ri.solution(matrix);
        Arrays.stream(matrix).flatMapToInt(Arrays::stream).forEach(System.out::print);
    }

    public void solution(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = i+1; j < n; j++) {
                if(i!=j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

//        Arrays.stream(matrix).forEach(ele-> System.out.println(Arrays.toString(ele)));

        for(int i = 0; i < n/2; i++) {
            for(int j = 0; j < m; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }
}
