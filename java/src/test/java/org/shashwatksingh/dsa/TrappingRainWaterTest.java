package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Trapping Rain Water Tests")
class TrappingRainWaterTest {

    private TrappingRainWater instance;

    @BeforeEach
    void setUp() {
        instance = new TrappingRainWater();
    }

    // ═══════════════════════════════════════════════════════════
    //  trapBruteForce()  — brute force left/right max per bar, O(n²) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("trapBruteForce() — brute force left/right max per bar")
    class TrapBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [0,1,0,2,1,0,1,3,2,1,2,1] → 6")
        void testLeetCodeExample1() {
            assertEquals(6, instance.trapBruteForce(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,2,0,3,2,5] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, instance.trapBruteForce(new int[]{4, 2, 0, 3, 2, 5}));
        }

        @Test
        @DisplayName("Single bar: [5] → 0")
        void testSingleBar() {
            assertEquals(0, instance.trapBruteForce(new int[]{5}));
        }

        @Test
        @DisplayName("Two bars: [3,1] → 0")
        void testTwoBars() {
            assertEquals(0, instance.trapBruteForce(new int[]{3, 1}));
        }

        @Test
        @DisplayName("All-same heights: [3,3,3,3] → 0")
        void testAllSameHeights() {
            assertEquals(0, instance.trapBruteForce(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, instance.trapBruteForce(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 0")
        void testStrictlyIncreasing() {
            assertEquals(0, instance.trapBruteForce(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 0")
        void testStrictlyDecreasing() {
            assertEquals(0, instance.trapBruteForce(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Single valley between equal walls: [2,0,2] → 2")
        void testSingleValley() {
            assertEquals(2, instance.trapBruteForce(new int[]{2, 0, 2}));
        }

        @Test
        @DisplayName("Peak with zeros on both sides: [0,5,0] → 0")
        void testPeakNoWalls() {
            assertEquals(0, instance.trapBruteForce(new int[]{0, 5, 0}));
        }

        @Test
        @DisplayName("Wide flat basin: [3,0,0,3] → 6")
        void testWideFlatBasin() {
            assertEquals(6, instance.trapBruteForce(new int[]{3, 0, 0, 3}));
        }

        @Test
        @DisplayName("Asymmetric walls, tall left: [5,0,0,0,1] → 3")
        void testAsymmetricTallLeft() {
            assertEquals(3, instance.trapBruteForce(new int[]{5, 0, 0, 0, 1}));
        }

        @Test
        @DisplayName("Asymmetric walls, stepped: [5,2,3,0,4] → 7")
        void testAsymmetricStepped() {
            assertEquals(7, instance.trapBruteForce(new int[]{5, 2, 3, 0, 4}));
        }

        @Test
        @DisplayName("Nested basins: [5,1,3,1,5] → 10")
        void testNestedBasins() {
            assertEquals(10, instance.trapBruteForce(new int[]{5, 1, 3, 1, 5}));
        }

        @Test
        @DisplayName("Multiple separate basins: [3,0,3,0,3] → 6")
        void testMultipleBasins() {
            assertEquals(6, instance.trapBruteForce(new int[]{3, 0, 3, 0, 3}));
        }

        @Test
        @DisplayName("Zero-height bars at both ends: [0,2,0,1,0] → 1")
        void testZeroEnds() {
            assertEquals(1, instance.trapBruteForce(new int[]{0, 2, 0, 1, 0}));
        }

        @Test
        @DisplayName("Max height value: [100000,0,100000] → 100000")
        void testMaxHeightValue() {
            assertEquals(100000, instance.trapBruteForce(new int[]{100000, 0, 100000}));
        }

        @Test
        @DisplayName("Max size (n=20000): 100000 wall, 19998 zeros, 100000 wall → 1999800000")
        void testMaxSizeDeepBasin() {
            int[] height = new int[20000];
            height[0] = 100000;
            height[19999] = 100000;
            assertEquals(1_999_800_000, instance.trapBruteForce(height));
        }

        @Test
        @DisplayName("Max size (n=20000): alternating [1,0,1,0,...] → 9999")
        void testMaxSizeAlternating() {
            int[] height = new int[20000];
            for (int i = 0; i < height.length; i += 2) {
                height[i] = 1;
            }
            assertEquals(9999, instance.trapBruteForce(height));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  trapMemoization()  — precomputed prefix/suffix max arrays, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("trapMemoization() — precomputed prefix/suffix max arrays")
    class TrapMemoizationTests {

        @Test
        @DisplayName("LeetCode Example 1: [0,1,0,2,1,0,1,3,2,1,2,1] → 6")
        void testLeetCodeExample1() {
            assertEquals(6, instance.trapMemoization(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,2,0,3,2,5] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, instance.trapMemoization(new int[]{4, 2, 0, 3, 2, 5}));
        }

        @Test
        @DisplayName("Single bar: [5] → 0")
        void testSingleBar() {
            assertEquals(0, instance.trapMemoization(new int[]{5}));
        }

        @Test
        @DisplayName("Two bars: [3,1] → 0")
        void testTwoBars() {
            assertEquals(0, instance.trapMemoization(new int[]{3, 1}));
        }

        @Test
        @DisplayName("All-same heights: [3,3,3,3] → 0")
        void testAllSameHeights() {
            assertEquals(0, instance.trapMemoization(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, instance.trapMemoization(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 0")
        void testStrictlyIncreasing() {
            assertEquals(0, instance.trapMemoization(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 0")
        void testStrictlyDecreasing() {
            assertEquals(0, instance.trapMemoization(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Single valley between equal walls: [2,0,2] → 2")
        void testSingleValley() {
            assertEquals(2, instance.trapMemoization(new int[]{2, 0, 2}));
        }

        @Test
        @DisplayName("Peak with zeros on both sides: [0,5,0] → 0")
        void testPeakNoWalls() {
            assertEquals(0, instance.trapMemoization(new int[]{0, 5, 0}));
        }

        @Test
        @DisplayName("Wide flat basin: [3,0,0,3] → 6")
        void testWideFlatBasin() {
            assertEquals(6, instance.trapMemoization(new int[]{3, 0, 0, 3}));
        }

        @Test
        @DisplayName("Asymmetric walls, tall left: [5,0,0,0,1] → 3")
        void testAsymmetricTallLeft() {
            assertEquals(3, instance.trapMemoization(new int[]{5, 0, 0, 0, 1}));
        }

        @Test
        @DisplayName("Asymmetric walls, stepped: [5,2,3,0,4] → 7")
        void testAsymmetricStepped() {
            assertEquals(7, instance.trapMemoization(new int[]{5, 2, 3, 0, 4}));
        }

        @Test
        @DisplayName("Nested basins: [5,1,3,1,5] → 10")
        void testNestedBasins() {
            assertEquals(10, instance.trapMemoization(new int[]{5, 1, 3, 1, 5}));
        }

        @Test
        @DisplayName("Multiple separate basins: [3,0,3,0,3] → 6")
        void testMultipleBasins() {
            assertEquals(6, instance.trapMemoization(new int[]{3, 0, 3, 0, 3}));
        }

        @Test
        @DisplayName("Zero-height bars at both ends: [0,2,0,1,0] → 1")
        void testZeroEnds() {
            assertEquals(1, instance.trapMemoization(new int[]{0, 2, 0, 1, 0}));
        }

        @Test
        @DisplayName("Max height value: [100000,0,100000] → 100000")
        void testMaxHeightValue() {
            assertEquals(100000, instance.trapMemoization(new int[]{100000, 0, 100000}));
        }

        @Test
        @DisplayName("Max size (n=20000): 100000 wall, 19998 zeros, 100000 wall → 1999800000")
        void testMaxSizeDeepBasin() {
            int[] height = new int[20000];
            height[0] = 100000;
            height[19999] = 100000;
            assertEquals(1_999_800_000, instance.trapMemoization(height));
        }

        @Test
        @DisplayName("Max size (n=20000): alternating [1,0,1,0,...] → 9999")
        void testMaxSizeAlternating() {
            int[] height = new int[20000];
            for (int i = 0; i < height.length; i += 2) {
                height[i] = 1;
            }
            assertEquals(9999, instance.trapMemoization(height));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  trapStack()  — monotonic stack, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("trapStack() — monotonic stack")
    class TrapStackTests {

        @Test
        @DisplayName("LeetCode Example 1: [0,1,0,2,1,0,1,3,2,1,2,1] → 6")
        void testLeetCodeExample1() {
            assertEquals(6, instance.trapStack(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,2,0,3,2,5] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, instance.trapStack(new int[]{4, 2, 0, 3, 2, 5}));
        }

        @Test
        @DisplayName("Single bar: [5] → 0")
        void testSingleBar() {
            assertEquals(0, instance.trapStack(new int[]{5}));
        }

        @Test
        @DisplayName("Two bars: [3,1] → 0")
        void testTwoBars() {
            assertEquals(0, instance.trapStack(new int[]{3, 1}));
        }

        @Test
        @DisplayName("All-same heights: [3,3,3,3] → 0")
        void testAllSameHeights() {
            assertEquals(0, instance.trapStack(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, instance.trapStack(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 0")
        void testStrictlyIncreasing() {
            assertEquals(0, instance.trapStack(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 0")
        void testStrictlyDecreasing() {
            assertEquals(0, instance.trapStack(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Single valley between equal walls: [2,0,2] → 2")
        void testSingleValley() {
            assertEquals(2, instance.trapStack(new int[]{2, 0, 2}));
        }

        @Test
        @DisplayName("Peak with zeros on both sides: [0,5,0] → 0")
        void testPeakNoWalls() {
            assertEquals(0, instance.trapStack(new int[]{0, 5, 0}));
        }

        @Test
        @DisplayName("Wide flat basin: [3,0,0,3] → 6")
        void testWideFlatBasin() {
            assertEquals(6, instance.trapStack(new int[]{3, 0, 0, 3}));
        }

        @Test
        @DisplayName("Asymmetric walls, tall left: [5,0,0,0,1] → 3")
        void testAsymmetricTallLeft() {
            assertEquals(3, instance.trapStack(new int[]{5, 0, 0, 0, 1}));
        }

        @Test
        @DisplayName("Asymmetric walls, stepped: [5,2,3,0,4] → 7")
        void testAsymmetricStepped() {
            assertEquals(7, instance.trapStack(new int[]{5, 2, 3, 0, 4}));
        }

        @Test
        @DisplayName("Nested basins: [5,1,3,1,5] → 10")
        void testNestedBasins() {
            assertEquals(10, instance.trapStack(new int[]{5, 1, 3, 1, 5}));
        }

        @Test
        @DisplayName("Multiple separate basins: [3,0,3,0,3] → 6")
        void testMultipleBasins() {
            assertEquals(6, instance.trapStack(new int[]{3, 0, 3, 0, 3}));
        }

        @Test
        @DisplayName("Zero-height bars at both ends: [0,2,0,1,0] → 1")
        void testZeroEnds() {
            assertEquals(1, instance.trapStack(new int[]{0, 2, 0, 1, 0}));
        }

        @Test
        @DisplayName("Max height value: [100000,0,100000] → 100000")
        void testMaxHeightValue() {
            assertEquals(100000, instance.trapStack(new int[]{100000, 0, 100000}));
        }

        @Test
        @DisplayName("Max size (n=20000): 100000 wall, 19998 zeros, 100000 wall → 1999800000")
        void testMaxSizeDeepBasin() {
            int[] height = new int[20000];
            height[0] = 100000;
            height[19999] = 100000;
            assertEquals(1_999_800_000, instance.trapStack(height));
        }

        @Test
        @DisplayName("Max size (n=20000): alternating [1,0,1,0,...] → 9999")
        void testMaxSizeAlternating() {
            int[] height = new int[20000];
            for (int i = 0; i < height.length; i += 2) {
                height[i] = 1;
            }
            assertEquals(9999, instance.trapStack(height));
        }
    }
}
