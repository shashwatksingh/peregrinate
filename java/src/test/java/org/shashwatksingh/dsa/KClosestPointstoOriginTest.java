package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("K Closest Points to Origin Tests")
class KClosestPointstoOriginTest {

    private KClosestPointstoOrigin instance;

    @BeforeEach
    void setUp() {
        instance = new KClosestPointstoOrigin();
    }

    /** The answer may be returned in any order, so sort by distance, then x, then y before comparing. */
    private static String normalize(int[][] points) {
        int[][] copy = Arrays.stream(points).map(int[]::clone).toArray(int[][]::new);
        Arrays.sort(copy, Comparator
                .comparingLong((int[] p) -> (long) p[0] * p[0] + (long) p[1] * p[1])
                .thenComparingInt(p -> p[0])
                .thenComparingInt(p -> p[1]));
        return Arrays.deepToString(copy);
    }

    // ═══════════════════════════════════════════════════════════
    //  kClosest()  — max-heap of size k, O(n log k) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("kClosest() — max-heap of size k")
    class KClosestTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,3],[-2,2]], k=1 → [[-2,2]]")
        void testLeetCodeExample1() {
            int[][] points = {{1, 3}, {-2, 2}};
            int[][] expected = {{-2, 2}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[3,3],[5,-1],[-2,4]], k=2 → [[3,3],[-2,4]]")
        void testLeetCodeExample2() {
            int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
            int[][] expected = {{3, 3}, {-2, 4}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Single point, k=1: [[0,0]], k=1 → [[0,0]]")
        void testSinglePoint() {
            int[][] points = {{0, 0}};
            int[][] expected = {{0, 0}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("k equals number of points returns all: [[3,3],[1,1],[2,2]], k=3 → [[3,3],[1,1],[2,2]]")
        void testKEqualsLength() {
            int[][] points = {{3, 3}, {1, 1}, {2, 2}};
            int[][] expected = {{3, 3}, {1, 1}, {2, 2}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 3)));
        }

        @Test
        @DisplayName("Point at the origin is closest: [[2,2],[0,0],[1,1]], k=1 → [[0,0]]")
        void testPointAtOrigin() {
            int[][] points = {{2, 2}, {0, 0}, {1, 1}};
            int[][] expected = {{0, 0}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("Duplicate points inside the selection: [[1,1],[1,1],[5,5]], k=2 → [[1,1],[1,1]]")
        void testDuplicatePoints() {
            int[][] points = {{1, 1}, {1, 1}, {5, 5}};
            int[][] expected = {{1, 1}, {1, 1}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Equidistant points all selected: [[1,0],[0,1],[-1,0],[0,-1],[9,9]], k=4 → [[1,0],[0,1],[-1,0],[0,-1]]")
        void testEquidistantPoints() {
            int[][] points = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {9, 9}};
            int[][] expected = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 4)));
        }

        @Test
        @DisplayName("All negative coordinates: [[-5,-5],[-1,-1],[-3,-3]], k=2 → [[-1,-1],[-3,-3]]")
        void testNegativeCoordinates() {
            int[][] points = {{-5, -5}, {-1, -1}, {-3, -3}};
            int[][] expected = {{-1, -1}, {-3, -3}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Already sorted by distance, ascending: [[1,1],[2,2],[3,3],[4,4]], k=2 → [[1,1],[2,2]]")
        void testSortedAscending() {
            int[][] points = {{1, 1}, {2, 2}, {3, 3}, {4, 4}};
            int[][] expected = {{1, 1}, {2, 2}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Sorted by distance, descending: [[4,4],[3,3],[2,2],[1,1]], k=2 → [[2,2],[1,1]]")
        void testSortedDescending() {
            int[][] points = {{4, 4}, {3, 3}, {2, 2}, {1, 1}};
            int[][] expected = {{2, 2}, {1, 1}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Closest point is last: [[9,9],[8,8],[1,0]], k=1 → [[1,0]]")
        void testClosestPointLast() {
            int[][] points = {{9, 9}, {8, 8}, {1, 0}};
            int[][] expected = {{1, 0}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("Points on the axes: [[0,5],[5,0],[0,-2],[-3,0]], k=2 → [[0,-2],[-3,0]]")
        void testAxisPoints() {
            int[][] points = {{0, 5}, {5, 0}, {0, -2}, {-3, 0}};
            int[][] expected = {{0, -2}, {-3, 0}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 2)));
        }

        @Test
        @DisplayName("Coordinate boundary values, k equals length: [[10000,10000],[-10000,10000],[1,1]], k=3 → [[10000,10000],[-10000,10000],[1,1]]")
        void testBoundaryValuesAll() {
            int[][] points = {{10000, 10000}, {-10000, 10000}, {1, 1}};
            int[][] expected = {{10000, 10000}, {-10000, 10000}, {1, 1}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 3)));
        }

        @Test
        @DisplayName("Coordinate boundary values, k=1: [[10000,10000],[-10000,-10000],[1,1]], k=1 → [[1,1]]")
        void testBoundaryValuesK1() {
            int[][] points = {{10000, 10000}, {-10000, -10000}, {1, 1}};
            int[][] expected = {{1, 1}};
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("Max size (n=10000), k=5000: points (±v,0) in descending order → the 5000 smallest |v|")
        void testMaxSizeHalf() {
            int n = 10000;
            int[][] points = new int[n][];
            for (int j = 0; j < n; j++) {
                int v = n - j;
                points[j] = new int[]{v % 2 == 0 ? v : -v, 0};
            }
            int[][] expected = new int[5000][];
            for (int v = 1; v <= 5000; v++) {
                expected[v - 1] = new int[]{v % 2 == 0 ? v : -v, 0};
            }
            assertEquals(normalize(expected), normalize(instance.kClosest(points, 5000)));
        }

        @Test
        @DisplayName("Max size (n=10000), k=1: points (±v,0) in descending order → [[-1,0]]")
        void testMaxSizeK1() {
            int n = 10000;
            int[][] points = new int[n][];
            for (int j = 0; j < n; j++) {
                int v = n - j;
                points[j] = new int[]{v % 2 == 0 ? v : -v, 0};
            }
            assertEquals(normalize(new int[][]{{-1, 0}}), normalize(instance.kClosest(points, 1)));
        }

        @Test
        @DisplayName("Max size (n=10000), k=n: all points are returned")
        void testMaxSizeKEqualsLength() {
            int n = 10000;
            int[][] points = new int[n][];
            for (int j = 0; j < n; j++) {
                int v = n - j;
                points[j] = new int[]{v % 2 == 0 ? v : -v, 0};
            }
            assertEquals(normalize(points), normalize(instance.kClosest(points, n)));
        }
    }
}
