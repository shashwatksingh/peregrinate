package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RectangleOverlap Tests")
class RectangleOverlapTest {

    private RectangleOverlap rectangleOverlap;

    @BeforeEach
    void setUp() {
        rectangleOverlap = new RectangleOverlap();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // solutionBoundaries
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("solutionBoundaries()")
    class SolutionBoundaries {

        @Test
        @DisplayName("Example 1: [0,0,2,2] and [1,1,3,3] → true (partial overlap)")
        void testPartialOverlap() {
            assertTrue(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        }

        @Test
        @DisplayName("Example 2: [0,0,1,1] and [1,0,2,1] → false (touching on right edge)")
        void testTouchingOnRightEdge() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        }

        @Test
        @DisplayName("Example 3: [0,0,1,1] and [2,2,3,3] → false (completely separated)")
        void testCompletelySeparated() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));
        }

        @Test
        @DisplayName("One rectangle fully inside the other → true")
        void testFullyContained() {
            assertTrue(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 10, 10}, new int[]{2, 2, 5, 5}));
        }

        @Test
        @DisplayName("Identical rectangles → true")
        void testSameRectangle() {
            assertTrue(rectangleOverlap.solutionBoundaries(
                    new int[]{1, 1, 4, 4}, new int[]{1, 1, 4, 4}));
        }

        @Test
        @DisplayName("Touching at corner only → false")
        void testTouchingAtCorner() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 1, 1}, new int[]{1, 1, 2, 2}));
        }

        @Test
        @DisplayName("rec1 is completely to the left of rec2 → false")
        void testRec1LeftOfRec2() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 1, 2}, new int[]{3, 0, 5, 2}));
        }

        @Test
        @DisplayName("rec1 is completely to the right of rec2 → false")
        void testRec1RightOfRec2() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{5, 0, 7, 2}, new int[]{0, 0, 3, 2}));
        }

        @Test
        @DisplayName("rec1 is completely above rec2 → false")
        void testRec1AboveRec2() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 5, 2, 8}, new int[]{0, 0, 2, 3}));
        }

        @Test
        @DisplayName("rec1 is completely below rec2 → false")
        void testRec1BelowRec2() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 2, 2}, new int[]{0, 5, 2, 8}));
        }

        @Test
        @DisplayName("Overlap with negative coordinates → true")
        void testNegativeCoordinatesOverlap() {
            assertTrue(rectangleOverlap.solutionBoundaries(
                    new int[]{-4, -4, -1, -1}, new int[]{-3, -3, 0, 0}));
        }

        @Test
        @DisplayName("No overlap with negative coordinates → false")
        void testNegativeCoordinatesNoOverlap() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{-5, -5, -3, -3}, new int[]{-1, -1, 1, 1}));
        }

        @Test
        @DisplayName("Large coordinate values with overlap → true")
        void testLargeCoordinatesOverlap() {
            assertTrue(rectangleOverlap.solutionBoundaries(
                    new int[]{-1000000000, -1000000000, 1000000000, 1000000000},
                    new int[]{0, 0, 500000000, 500000000}));
        }

        @Test
        @DisplayName("Touching on top edge → false")
        void testTouchingOnTopEdge() {
            assertFalse(rectangleOverlap.solutionBoundaries(
                    new int[]{0, 0, 2, 2}, new int[]{0, 2, 2, 4}));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // solutionArea
    // ─────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("solutionArea()")
    class SolutionArea {

        @Test
        @DisplayName("Example 1: [0,0,2,2] and [1,1,3,3] → true (partial overlap)")
        void testPartialOverlap() {
            assertTrue(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        }

        @Test
        @DisplayName("Example 2: [0,0,1,1] and [1,0,2,1] → false (touching on right edge)")
        void testTouchingOnRightEdge() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        }

        @Test
        @DisplayName("Example 3: [0,0,1,1] and [2,2,3,3] → false (completely separated)")
        void testCompletelySeparated() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));
        }

        @Test
        @DisplayName("One rectangle fully inside the other → true")
        void testFullyContained() {
            assertTrue(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 10, 10}, new int[]{2, 2, 5, 5}));
        }

        @Test
        @DisplayName("Identical rectangles → true")
        void testSameRectangle() {
            assertTrue(rectangleOverlap.solutionArea(
                    new int[]{1, 1, 4, 4}, new int[]{1, 1, 4, 4}));
        }

        @Test
        @DisplayName("Touching at corner only → false")
        void testTouchingAtCorner() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 1, 1}, new int[]{1, 1, 2, 2}));
        }

        @Test
        @DisplayName("rec1 is completely to the left of rec2 → false")
        void testRec1LeftOfRec2() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 1, 2}, new int[]{3, 0, 5, 2}));
        }

        @Test
        @DisplayName("rec1 is completely to the right of rec2 → false")
        void testRec1RightOfRec2() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{5, 0, 7, 2}, new int[]{0, 0, 3, 2}));
        }

        @Test
        @DisplayName("rec1 is completely above rec2 → false")
        void testRec1AboveRec2() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 5, 2, 8}, new int[]{0, 0, 2, 3}));
        }

        @Test
        @DisplayName("rec1 is completely below rec2 → false")
        void testRec1BelowRec2() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 2, 2}, new int[]{0, 5, 2, 8}));
        }

        @Test
        @DisplayName("Overlap with negative coordinates → true")
        void testNegativeCoordinatesOverlap() {
            assertTrue(rectangleOverlap.solutionArea(
                    new int[]{-4, -4, -1, -1}, new int[]{-3, -3, 0, 0}));
        }

        @Test
        @DisplayName("No overlap with negative coordinates → false")
        void testNegativeCoordinatesNoOverlap() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{-5, -5, -3, -3}, new int[]{-1, -1, 1, 1}));
        }

        @Test
        @DisplayName("Large coordinate values with overlap → true")
        void testLargeCoordinatesOverlap() {
            assertTrue(rectangleOverlap.solutionArea(
                    new int[]{-1000000000, -1000000000, 1000000000, 1000000000},
                    new int[]{0, 0, 500000000, 500000000}));
        }

        @Test
        @DisplayName("Touching on top edge → false")
        void testTouchingOnTopEdge() {
            assertFalse(rectangleOverlap.solutionArea(
                    new int[]{0, 0, 2, 2}, new int[]{0, 2, 2, 4}));
        }
    }
}