package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Remove Interval Tests")
class RemoveIntervalTest {

    private RemoveInterval instance;

    @BeforeEach
    void setUp() {
        instance = new RemoveInterval();
    }

    // ═══════════════════════════════════════════════════════════
    //  removeInterval()  — single pass over sorted disjoint intervals, O(n) time, O(n) space
    //  (intervals are half-open: [a, b) contains x when a <= x < b)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("removeInterval() — single pass over sorted disjoint half-open intervals")
    class RemoveIntervalTests {

        @Test
        @DisplayName("LeetCode Example 1: [[0,2],[3,4],[5,7]], remove [1,6) → [[0,1],[6,7]]")
        void testLeetCodeExample1() {
            List<List<Integer>> expected = List.of(List.of(0, 1), List.of(6, 7));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 2}, {3, 4}, {5, 7}}, new int[]{1, 6}).toString());
        }

        @Test
        @DisplayName("LeetCode Example 2: [[0,5]], remove [2,3) → [[0,2],[3,5]]")
        void testLeetCodeExample2() {
            List<List<Integer>> expected = List.of(List.of(0, 2), List.of(3, 5));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 5}}, new int[]{2, 3}).toString());
        }

        @Test
        @DisplayName("LeetCode Example 3: [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], remove [-1,4) → [[-5,-4],[-3,-2],[4,5],[8,9]]")
        void testLeetCodeExample3() {
            List<List<Integer>> expected = List.of(List.of(-5, -4), List.of(-3, -2), List.of(4, 5), List.of(8, 9));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{-5, -4}, {-3, -2}, {1, 2}, {3, 5}, {8, 9}}, new int[]{-1, 4}).toString());
        }

        @Test
        @DisplayName("Single interval fully removed: [[1,3]], remove [0,5) → []")
        void testSingleIntervalFullyRemoved() {
            List<List<Integer>> expected = List.of();
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{1, 3}}, new int[]{0, 5}).toString());
        }

        @Test
        @DisplayName("Removal identical to the only interval: [[1,3]], remove [1,3) → []")
        void testRemovalEqualsInterval() {
            List<List<Integer>> expected = List.of();
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{1, 3}}, new int[]{1, 3}).toString());
        }

        @Test
        @DisplayName("Removal entirely before all intervals: [[5,7]], remove [1,3) → [[5,7]]")
        void testRemovalBefore() {
            List<List<Integer>> expected = List.of(List.of(5, 7));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{5, 7}}, new int[]{1, 3}).toString());
        }

        @Test
        @DisplayName("Removal entirely after all intervals: [[1,3]], remove [5,7) → [[1,3]]")
        void testRemovalAfter() {
            List<List<Integer>> expected = List.of(List.of(1, 3));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{1, 3}}, new int[]{5, 7}).toString());
        }

        @Test
        @DisplayName("Removal starts exactly where the interval ends (half-open, no overlap): [[1,3]], remove [3,5) → [[1,3]]")
        void testRemovalTouchesRightEnd() {
            List<List<Integer>> expected = List.of(List.of(1, 3));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{1, 3}}, new int[]{3, 5}).toString());
        }

        @Test
        @DisplayName("Removal ends exactly where the interval starts (half-open, no overlap): [[3,5]], remove [1,3) → [[3,5]]")
        void testRemovalTouchesLeftEnd() {
            List<List<Integer>> expected = List.of(List.of(3, 5));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{3, 5}}, new int[]{1, 3}).toString());
        }

        @Test
        @DisplayName("Removal cuts a prefix: [[0,5]], remove [0,2) → [[2,5]]")
        void testRemovalCutsPrefix() {
            List<List<Integer>> expected = List.of(List.of(2, 5));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 5}}, new int[]{0, 2}).toString());
        }

        @Test
        @DisplayName("Removal cuts a suffix: [[0,5]], remove [3,5) → [[0,3]]")
        void testRemovalCutsSuffix() {
            List<List<Integer>> expected = List.of(List.of(0, 3));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 5}}, new int[]{3, 5}).toString());
        }

        @Test
        @DisplayName("Removal overhangs on the left: [[2,5]], remove [0,3) → [[3,5]]")
        void testRemovalOverhangsLeft() {
            List<List<Integer>> expected = List.of(List.of(3, 5));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{2, 5}}, new int[]{0, 3}).toString());
        }

        @Test
        @DisplayName("Removal overhangs on the right: [[2,5]], remove [4,9) → [[2,4]]")
        void testRemovalOverhangsRight() {
            List<List<Integer>> expected = List.of(List.of(2, 4));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{2, 5}}, new int[]{4, 9}).toString());
        }

        @Test
        @DisplayName("Removal swallows every interval: [[0,1],[2,3],[4,5],[6,7]], remove [0,7) → []")
        void testRemovalSwallowsAll() {
            List<List<Integer>> expected = List.of();
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 1}, {2, 3}, {4, 5}, {6, 7}}, new int[]{0, 7}).toString());
        }

        @Test
        @DisplayName("Removal covers the middle intervals and trims the outer ones: [[0,2],[3,4],[5,6],[7,9]], remove [1,8) → [[0,1],[8,9]]")
        void testRemovalTrimsOuterIntervals() {
            List<List<Integer>> expected = List.of(List.of(0, 1), List.of(8, 9));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 2}, {3, 4}, {5, 6}, {7, 9}}, new int[]{1, 8}).toString());
        }

        @Test
        @DisplayName("Removal sits in a gap between intervals: [[0,2],[5,7]], remove [3,4) → [[0,2],[5,7]]")
        void testRemovalInGap() {
            List<List<Integer>> expected = List.of(List.of(0, 2), List.of(5, 7));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 2}, {5, 7}}, new int[]{3, 4}).toString());
        }

        @Test
        @DisplayName("Removal exactly fills a gap between two intervals: [[0,2],[3,5]], remove [2,3) → [[0,2],[3,5]]")
        void testRemovalFillsGap() {
            List<List<Integer>> expected = List.of(List.of(0, 2), List.of(3, 5));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{0, 2}, {3, 5}}, new int[]{2, 3}).toString());
        }

        @Test
        @DisplayName("Negative coordinates: [[-10,-5],[-3,0]], remove [-7,-1) → [[-10,-7],[-1,0]]")
        void testNegativeCoordinates() {
            List<List<Integer>> expected = List.of(List.of(-10, -7), List.of(-1, 0));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{-10, -5}, {-3, 0}}, new int[]{-7, -1}).toString());
        }

        @Test
        @DisplayName("Coordinate boundary: split ±1e9 interval: [[-1000000000,1000000000]], remove [0,1) → [[-1000000000,0],[1,1000000000]]")
        void testBoundarySplit() {
            List<List<Integer>> expected = List.of(List.of(-1000000000, 0), List.of(1, 1000000000));
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{-1000000000, 1000000000}}, new int[]{0, 1}).toString());
        }

        @Test
        @DisplayName("Coordinate boundary: remove the whole ±1e9 interval: [[-1000000000,1000000000]], remove [-1000000000,1000000000) → []")
        void testBoundaryRemoveAll() {
            List<List<Integer>> expected = List.of();
            assertEquals(expected.toString(), instance.removeInterval(new int[][]{{-1000000000, 1000000000}}, new int[]{-1000000000, 1000000000}).toString());
        }

        @Test
        @DisplayName("Max size (n=10000): intervals [2i,2i+1), remove [5000,15000) → intervals before 5000 and from 15000 on")
        void testMaxSizeRemovalInTheMiddle() {
            int n = 10000;
            int[][] intervals = new int[n][];
            List<List<Integer>> expected = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                intervals[i] = new int[]{2 * i, 2 * i + 1};
                if (2 * i + 1 <= 5000 || 2 * i >= 15000) {
                    expected.add(List.of(2 * i, 2 * i + 1));
                }
            }
            assertEquals(expected.toString(), instance.removeInterval(intervals, new int[]{5000, 15000}).toString());
        }

        @Test
        @DisplayName("Max size (n=10000): intervals [2i,2i+1), remove [30000,40000) beyond every interval → all intervals unchanged")
        void testMaxSizeNoOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            List<List<Integer>> expected = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                intervals[i] = new int[]{2 * i, 2 * i + 1};
                expected.add(List.of(2 * i, 2 * i + 1));
            }
            assertEquals(expected.toString(), instance.removeInterval(intervals, new int[]{30000, 40000}).toString());
        }
    }
}
