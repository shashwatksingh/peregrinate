package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rotate Image Tests")
public class RotateImageTest {

    private RotateImage rotateImage;

    @BeforeEach
    void setUp() {
        rotateImage = new RotateImage();
    }

    // ── Examples from LeetCode ──────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: [[1,2,3],[4,5,6],[7,8,9]] → [[7,4,1],[8,5,2],[9,6,3]]")
    void testExample1_3x3() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{7,4,1},{8,5,2},{9,6,3}}, matrix);
    }

    @Test
    @DisplayName("Example 2: [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]] → [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]")
    void testExample2_4x4() {
        int[][] matrix = {
            {5,  1,  9, 11},
            {2,  4,  8, 10},
            {13, 3,  6,  7},
            {15, 14, 12, 16}
        };
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{
            {15, 13,  2,  5},
            {14,  3,  4,  1},
            {12,  6,  8,  9},
            {16,  7, 10, 11}
        }, matrix);
    }

    // ── Edge / stress cases ─────────────────────────────────────────────────

    @Test
    @DisplayName("1×1 matrix: single element stays unchanged")
    void test1x1() {
        int[][] matrix = {{42}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{42}}, matrix);
    }

    @Test
    @DisplayName("2×2 matrix: [[1,2],[3,4]] → [[3,1],[4,2]]")
    void test2x2() {
        int[][] matrix = {{1,2},{3,4}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{3,1},{4,2}}, matrix);
    }

    @Test
    @DisplayName("Rotating 4 times returns to original (identity check)")
    void testFourRotationsIsIdentity() {
        int[][] original = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix   = {{1,2,3},{4,5,6},{7,8,9}};
        for (int i = 0; i < 4; i++) rotateImage.solution(matrix);
        assertArrayEquals(original, matrix);
    }

    @Test
    @DisplayName("All same values: [[7,7],[7,7]] stays [[7,7],[7,7]]")
    void testAllSameValues() {
        int[][] matrix = {{7,7},{7,7}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{7,7},{7,7}}, matrix);
    }

    @Test
    @DisplayName("Negative values: [[-1,-2],[-3,-4]] → [[-3,-1],[-4,-2]]")
    void testNegativeValues() {
        int[][] matrix = {{-1,-2},{-3,-4}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{-3,-1},{-4,-2}}, matrix);
    }

    @Test
    @DisplayName("5×5 matrix rotation: corners cycle correctly")
    void test5x5_cornerCheck() {
        int[][] matrix = {
            { 1,  2,  3,  4,  5},
            { 6,  7,  8,  9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        rotateImage.solution(matrix);
        // After 90° CW rotation: element at (r,c) moves to (c, n-1-r)
        // Original (0,0)=1  → new position (0,4)
        assertEquals(1,  matrix[0][4]);
        // Original (0,4)=5  → new position (4,4)
        assertEquals(5,  matrix[4][4]);
        // Original (4,4)=25 → new position (4,0)
        assertEquals(25, matrix[4][0]);
        // Original (4,0)=21 → new position (0,0)
        assertEquals(21, matrix[0][0]);
        // Centre element is always fixed
        assertEquals(13, matrix[2][2]);
    }

    @Test
    @DisplayName("Integer boundary values: MIN_VALUE and MAX_VALUE preserved after rotation")
    void testIntegerBoundaryValues() {
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        int[][] matrix = {{min, max},{0, -1}};
        rotateImage.solution(matrix);
        assertArrayEquals(new int[][]{{0, min},{-1, max}}, matrix);
    }
}
