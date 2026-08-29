package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TwoSum Tests")
class TwoSumTest {

    private TwoSum twoSum;

    @BeforeEach
    void setUp() {
        twoSum = new TwoSum();
    }

    @Test
    @DisplayName("Example 1: [2,7,11,15] with target 9 → [0,1]")
    void testBasicCase() {
        int[] result = twoSum.solution(new int[]{2, 7, 11, 15}, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    @DisplayName("Example 2: [3,2,4] with target 6 → [1,2]")
    void testComplementNotAtStart() {
        int[] result = twoSum.solution(new int[]{3, 2, 4}, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    @DisplayName("Example 3: [3,3] with target 6 → [0,1] (duplicate values)")
    void testDuplicateValues() {
        int[] result = twoSum.solution(new int[]{3, 3}, 6);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    @DisplayName("Negative numbers: [-1,-2,-3,-4,-5] with target -8 → [2,4]")
    void testNegativeNumbers() {
        int[] result = twoSum.solution(new int[]{-1, -2, -3, -4, -5}, -8);
        assertArrayEquals(new int[]{2, 4}, result);
    }

    @Test
    @DisplayName("Mixed positive and negative: [-3, 4, 3, 90] with target 0 → [0,2]")
    void testMixedPositiveAndNegative() {
        int[] result = twoSum.solution(new int[]{-3, 4, 3, 90}, 0);
        assertArrayEquals(new int[]{0, 2}, result);
    }

    @Test
    @DisplayName("Answer pair is at the end of the array")
    void testAnswerAtEnd() {
        int[] result = twoSum.solution(new int[]{1, 5, 8, 3, 7}, 10);
        assertArrayEquals(new int[]{3, 4}, result);
    }

    @Test
    @DisplayName("Result array has exactly 2 elements")
    void testResultLength() {
        int[] result = twoSum.solution(new int[]{2, 7, 11, 15}, 9);
        assertEquals(2, result.length);
    }

    @Test
    @DisplayName("Returned indices are in ascending order")
    void testIndicesAreOrdered() {
        int[] result = twoSum.solution(new int[]{2, 7, 11, 15}, 9);
        assertTrue(result[0] < result[1], "First index should be less than second index");
    }
}
