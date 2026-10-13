package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Merge Intervals Tests")
class MergeIntervalsTest {

    private MergeIntervals instance;

    @BeforeEach
    void setUp() {
        instance = new MergeIntervals();
    }

    // ═══════════════════════════════════════════════════════════
    //  merge()  — sort by start, then merge overlaps, O(n log n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("merge() — sort by start, then merge overlaps")
    class MergeTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]]")
        void testLeetCodeExample1() {
            int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
            int[][] expected = new int[][]{{1, 6}, {8, 10}, {15, 18}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,4],[4,5]] → [[1,5]]")
        void testLeetCodeExample2() {
            int[][] intervals = new int[][]{{1, 4}, {4, 5}};
            int[][] expected = new int[][]{{1, 5}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [[4,7],[1,4]] → [[1,7]]")
        void testLeetCodeExample3() {
            int[][] intervals = new int[][]{{4, 7}, {1, 4}};
            int[][] expected = new int[][]{{1, 7}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Single interval: [[2,5]] → [[2,5]]")
        void testSingleInterval() {
            int[][] intervals = new int[][]{{2, 5}};
            int[][] expected = new int[][]{{2, 5}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Single zero-length interval: [[0,0]] → [[0,0]]")
        void testSingleZeroLengthInterval() {
            int[][] intervals = new int[][]{{0, 0}};
            int[][] expected = new int[][]{{0, 0}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Identical intervals: [[1,3],[1,3]] → [[1,3]]")
        void testIdenticalIntervals() {
            int[][] intervals = new int[][]{{1, 3}, {1, 3}};
            int[][] expected = new int[][]{{1, 3}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Interval contained in another: [[1,10],[2,3]] → [[1,10]]")
        void testContainedInterval() {
            int[][] intervals = new int[][]{{1, 10}, {2, 3}};
            int[][] expected = new int[][]{{1, 10}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Contained interval followed by an extending one: [[1,4],[2,3],[3,6]] → [[1,6]]")
        void testContainedThenExtending() {
            int[][] intervals = new int[][]{{1, 4}, {2, 3}, {3, 6}};
            int[][] expected = new int[][]{{1, 6}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Big interval swallowing several later ones: [[1,10],[2,3],[4,5],[6,7]] → [[1,10]]")
        void testBigIntervalSwallowsLaterOnes() {
            int[][] intervals = new int[][]{{1, 10}, {2, 3}, {4, 5}, {6, 7}};
            int[][] expected = new int[][]{{1, 10}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Disjoint intervals, sorted: [[1,2],[4,5],[7,8]] → [[1,2],[4,5],[7,8]]")
        void testDisjointSorted() {
            int[][] intervals = new int[][]{{1, 2}, {4, 5}, {7, 8}};
            int[][] expected = new int[][]{{1, 2}, {4, 5}, {7, 8}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Disjoint intervals, unsorted: [[7,8],[1,2],[4,5]] → [[1,2],[4,5],[7,8]]")
        void testDisjointUnsorted() {
            int[][] intervals = new int[][]{{7, 8}, {1, 2}, {4, 5}};
            int[][] expected = new int[][]{{1, 2}, {4, 5}, {7, 8}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Chain of touching intervals: [[1,2],[2,3],[3,4],[4,5]] → [[1,5]]")
        void testChainOfTouchingIntervals() {
            int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}};
            int[][] expected = new int[][]{{1, 5}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Everything overlaps, unsorted: [[1,4],[0,2],[3,5]] → [[0,5]]")
        void testAllOverlapUnsorted() {
            int[][] intervals = new int[][]{{1, 4}, {0, 2}, {3, 5}};
            int[][] expected = new int[][]{{0, 5}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Same start, different ends: [[1,3],[1,5],[1,2]] → [[1,5]]")
        void testSameStartDifferentEnds() {
            int[][] intervals = new int[][]{{1, 3}, {1, 5}, {1, 2}};
            int[][] expected = new int[][]{{1, 5}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Identical zero-length intervals: [[2,2],[2,2]] → [[2,2]]")
        void testIdenticalZeroLengthIntervals() {
            int[][] intervals = new int[][]{{2, 2}, {2, 2}};
            int[][] expected = new int[][]{{2, 2}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Distinct zero-length intervals: [[1,1],[2,2]] → [[1,1],[2,2]]")
        void testDistinctZeroLengthIntervals() {
            int[][] intervals = new int[][]{{1, 1}, {2, 2}};
            int[][] expected = new int[][]{{1, 1}, {2, 2}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Zero-length interval touching a normal one: [[1,2],[2,2]] → [[1,2]]")
        void testZeroLengthTouching() {
            int[][] intervals = new int[][]{{1, 2}, {2, 2}};
            int[][] expected = new int[][]{{1, 2}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Several separate merged groups: [[1,3],[2,4],[6,8],[7,9],[12,13]] → [[1,4],[6,9],[12,13]]")
        void testMultipleMergedGroups() {
            int[][] intervals = new int[][]{{1, 3}, {2, 4}, {6, 8}, {7, 9}, {12, 13}};
            int[][] expected = new int[][]{{1, 4}, {6, 9}, {12, 13}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Reverse-sorted overlapping groups: [[8,10],[6,9],[1,3],[2,4]] → [[1,4],[6,10]]")
        void testReverseSortedGroups() {
            int[][] intervals = new int[][]{{8, 10}, {6, 9}, {1, 3}, {2, 4}};
            int[][] expected = new int[][]{{1, 4}, {6, 10}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Max coordinate range, single interval: [[0,10000]] → [[0,10000]]")
        void testMaxRangeSingle() {
            int[][] intervals = new int[][]{{0, 10000}};
            int[][] expected = new int[][]{{0, 10000}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Max coordinate range, touching halves: [[0,5000],[5000,10000]] → [[0,10000]]")
        void testMaxRangeTouching() {
            int[][] intervals = new int[][]{{0, 5000}, {5000, 10000}};
            int[][] expected = new int[][]{{0, 10000}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Max coordinate range, halves with a gap: [[0,4999],[5000,10000]] → [[0,4999],[5000,10000]]")
        void testMaxRangeGap() {
            int[][] intervals = new int[][]{{0, 4999}, {5000, 10000}};
            int[][] expected = new int[][]{{0, 4999}, {5000, 10000}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Max size (n=10000): touching intervals [i,i+1] in reverse order → [[0,10000]]")
        void testMaxSizeChain() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int start = n - 1 - i;
                intervals[i] = new int[]{start, start + 1};
            }
            int[][] expected = {{0, 10000}};
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }

        @Test
        @DisplayName("Max size (n=10000): disjoint points [i,i] in reverse order → [[0,0],[1,1],...,[9999,9999]]")
        void testMaxSizeDisjoint() {
            int n = 10000;
            int[][] intervals = new int[n][];
            int[][] expected = new int[n][];
            for (int i = 0; i < n; i++) {
                intervals[i] = new int[]{n - 1 - i, n - 1 - i};
                expected[i] = new int[]{i, i};
            }
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(instance.merge(intervals)));
        }
    }
}
