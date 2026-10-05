package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Min Stack Tests")
class MinStackTest {

    private MinStack minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack();
    }

    // ═══════════════════════════════════════════════════════════
    //  push()  — O(1) time (observed through top() and getMin())
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("push() — pushes a value, O(1)")
    class PushTests {

        @Test
        @DisplayName("Scenario: push(5) → top 5, min 5 (single element)")
        void testPushSingleElement() {
            minStack.push(5);
            assertEquals(5, minStack.top());
            assertEquals(5, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(1), push(2), push(3) → top 3, min 1 (strictly increasing)")
        void testPushIncreasing() {
            minStack.push(1);
            minStack.push(2);
            minStack.push(3);
            assertEquals(3, minStack.top());
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(3), push(2), push(1) → top 1, min 1 (strictly decreasing)")
        void testPushDecreasing() {
            minStack.push(3);
            minStack.push(2);
            minStack.push(1);
            assertEquals(1, minStack.top());
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(2), push(2) → top 2, min 2 (duplicates)")
        void testPushDuplicates() {
            minStack.push(2);
            minStack.push(2);
            assertEquals(2, minStack.top());
            assertEquals(2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(0) → top 0, min 0 (zero)")
        void testPushZero() {
            minStack.push(0);
            assertEquals(0, minStack.top());
            assertEquals(0, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(-1), push(-5), push(-3) → top -3, min -5 (negatives)")
        void testPushNegatives() {
            minStack.push(-1);
            minStack.push(-5);
            minStack.push(-3);
            assertEquals(-3, minStack.top());
            assertEquals(-5, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MAX_VALUE), push(MIN_VALUE) → top MIN_VALUE, min MIN_VALUE (int extremes)")
        void testPushMaxThenMin() {
            minStack.push(Integer.MAX_VALUE);
            minStack.push(Integer.MIN_VALUE);
            assertEquals(Integer.MIN_VALUE, minStack.top());
            assertEquals(Integer.MIN_VALUE, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MIN_VALUE), push(MAX_VALUE) → top MAX_VALUE, min MIN_VALUE (int extremes)")
        void testPushMinThenMax() {
            minStack.push(Integer.MIN_VALUE);
            minStack.push(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, minStack.top());
            assertEquals(Integer.MIN_VALUE, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MAX_VALUE) → top MAX_VALUE, min MAX_VALUE (single extreme)")
        void testPushOnlyMaxValue() {
            minStack.push(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, minStack.top());
            assertEquals(Integer.MAX_VALUE, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: 29,998 ascending pushes (0..29,997) → top 29,997, min 0 (max call count)")
        void testPushMaxCallsAscending() {
            for (int i = 0; i < 29_998; i++) {
                minStack.push(i);
            }
            assertEquals(29_997, minStack.top());
            assertEquals(0, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: 29,998 descending pushes (29,997..0) → top 0, min 0 (max call count)")
        void testPushMaxCallsDescending() {
            for (int i = 29_997; i >= 0; i--) {
                minStack.push(i);
            }
            assertEquals(0, minStack.top());
            assertEquals(0, minStack.getMin());
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  pop()  — O(1) time (observed through top() and getMin())
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("pop() — removes the top element, O(1)")
    class PopTests {

        @Test
        @DisplayName("LeetCode Example 1: push(-2), push(0), push(-3), pop() → top 0, min -2")
        void testLeetCodeExample1() {
            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            minStack.pop();
            assertEquals(0, minStack.top());
            assertEquals(-2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(2), push(1), pop() → min 2 (popping the minimum restores previous min)")
        void testPopMinimumRestoresPreviousMin() {
            minStack.push(2);
            minStack.push(1);
            minStack.pop();
            assertEquals(2, minStack.top());
            assertEquals(2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(1), push(2), pop() → min 1 (popping a non-minimum keeps min)")
        void testPopNonMinimumKeepsMin() {
            minStack.push(1);
            minStack.push(2);
            minStack.pop();
            assertEquals(1, minStack.top());
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(1), push(1), pop() → min 1 (duplicate minimum survives one pop)")
        void testPopDuplicateMinimum() {
            minStack.push(1);
            minStack.push(1);
            minStack.pop();
            assertEquals(1, minStack.top());
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(1), push(2), push(3), pop(), pop() → top 1, min 1 (pop down to one element)")
        void testPopDownToSingleElement() {
            minStack.push(1);
            minStack.push(2);
            minStack.push(3);
            minStack.pop();
            minStack.pop();
            assertEquals(1, minStack.top());
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5), pop(), push(7) → top 7, min 7 (reuse after emptying)")
        void testPushAfterEmptying() {
            minStack.push(5);
            minStack.pop();
            minStack.push(7);
            assertEquals(7, minStack.top());
            assertEquals(7, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5,3,4,2,6), pop() → top 2, min 2 (first pop of mixed sequence)")
        void testPopMixedSequenceFirst() {
            pushAll(5, 3, 4, 2, 6);
            minStack.pop();
            assertEquals(2, minStack.top());
            assertEquals(2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5,3,4,2,6), pop() ×2 → top 4, min 3 (min reverts after popping 2)")
        void testPopMixedSequenceSecond() {
            pushAll(5, 3, 4, 2, 6);
            minStack.pop();
            minStack.pop();
            assertEquals(4, minStack.top());
            assertEquals(3, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5,3,4,2,6), pop() ×3 → top 3, min 3")
        void testPopMixedSequenceThird() {
            pushAll(5, 3, 4, 2, 6);
            minStack.pop();
            minStack.pop();
            minStack.pop();
            assertEquals(3, minStack.top());
            assertEquals(3, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5,3,4,2,6), pop() ×4 → top 5, min 5 (min reverts after popping 3)")
        void testPopMixedSequenceFourth() {
            pushAll(5, 3, 4, 2, 6);
            minStack.pop();
            minStack.pop();
            minStack.pop();
            minStack.pop();
            assertEquals(5, minStack.top());
            assertEquals(5, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MIN_VALUE), push(MAX_VALUE), pop() → top MIN_VALUE, min MIN_VALUE")
        void testPopIntExtremes() {
            minStack.push(Integer.MIN_VALUE);
            minStack.push(Integer.MAX_VALUE);
            minStack.pop();
            assertEquals(Integer.MIN_VALUE, minStack.top());
            assertEquals(Integer.MIN_VALUE, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: 15,000 pushes then 14,999 pops → top 0, min 0 (max call count)")
        void testPopMaxCalls() {
            for (int i = 0; i < 15_000; i++) {
                minStack.push(i);
            }
            for (int i = 0; i < 14_999; i++) {
                minStack.pop();
            }
            assertEquals(0, minStack.top());
            assertEquals(0, minStack.getMin());
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  top()  — O(1) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("top() — returns the top element, O(1)")
    class TopTests {

        @Test
        @DisplayName("Scenario: push(4) → 4 (single element)")
        void testTopSingleElement() {
            minStack.push(4);
            assertEquals(4, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(1), push(9), push(5) → 5 (most recent push)")
        void testTopIsMostRecentPush() {
            minStack.push(1);
            minStack.push(9);
            minStack.push(5);
            assertEquals(5, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(1), push(9), push(5), pop() → 9 (after pop)")
        void testTopAfterPop() {
            minStack.push(1);
            minStack.push(9);
            minStack.push(5);
            minStack.pop();
            assertEquals(9, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(7), top(), top() → 7, 7 (top does not remove)")
        void testTopDoesNotRemove() {
            minStack.push(7);
            assertEquals(7, minStack.top());
            assertEquals(7, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(-8) → -8 (negative value)")
        void testTopNegative() {
            minStack.push(-8);
            assertEquals(-8, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(3), push(3), pop() → 3 (duplicates)")
        void testTopDuplicates() {
            minStack.push(3);
            minStack.push(3);
            minStack.pop();
            assertEquals(3, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(MIN_VALUE), push(MAX_VALUE) → MAX_VALUE (int extremes)")
        void testTopIntExtremes() {
            minStack.push(Integer.MIN_VALUE);
            minStack.push(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, minStack.top());
        }

        @Test
        @DisplayName("Scenario: push(1), push(2), pop(), pop(), push(3) → 3 (top after stack was emptied)")
        void testTopAfterEmptyAndRefill() {
            minStack.push(1);
            minStack.push(2);
            minStack.pop();
            minStack.pop();
            minStack.push(3);
            assertEquals(3, minStack.top());
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  getMin()  — O(1) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getMin() — returns the minimum element, O(1)")
    class GetMinTests {

        @Test
        @DisplayName("LeetCode Example 1: push(-2), push(0), push(-3) → -3")
        void testLeetCodeExample1() {
            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            assertEquals(-3, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(8) → 8 (single element)")
        void testGetMinSingleElement() {
            minStack.push(8);
            assertEquals(8, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(3), push(3), push(3) → 3 (all same)")
        void testGetMinAllSame() {
            minStack.push(3);
            minStack.push(3);
            minStack.push(3);
            assertEquals(3, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(1,2,3,4) → min 1 after each push (strictly increasing)")
        void testGetMinIncreasing() {
            minStack.push(1);
            assertEquals(1, minStack.getMin());
            minStack.push(2);
            assertEquals(1, minStack.getMin());
            minStack.push(3);
            assertEquals(1, minStack.getMin());
            minStack.push(4);
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(4,3,2,1) → min 4, 3, 2, 1 (strictly decreasing)")
        void testGetMinDecreasing() {
            minStack.push(4);
            assertEquals(4, minStack.getMin());
            minStack.push(3);
            assertEquals(3, minStack.getMin());
            minStack.push(2);
            assertEquals(2, minStack.getMin());
            minStack.push(1);
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(5), push(6), push(2), push(7) → 2 (new minimum mid-sequence)")
        void testGetMinNewMinimumMidSequence() {
            pushAll(5, 6, 2, 7);
            assertEquals(2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(2), push(5), push(1), push(4), push(1) → 1 (minimum pushed twice)")
        void testGetMinMinimumPushedTwice() {
            pushAll(2, 5, 1, 4, 1);
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(0), push(-1), push(1) → -1 (zero and negatives)")
        void testGetMinZeroAndNegative() {
            pushAll(0, -1, 1);
            assertEquals(-1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(2), push(1), push(1), pop() → 1 (duplicate minimum, one popped)")
        void testGetMinDuplicateMinimumOnePopped() {
            pushAll(2, 1, 1);
            minStack.pop();
            assertEquals(1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(2), push(1), push(1), pop(), pop() → 2 (duplicate minimum, both popped)")
        void testGetMinDuplicateMinimumBothPopped() {
            pushAll(2, 1, 1);
            minStack.pop();
            minStack.pop();
            assertEquals(2, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(4), getMin(), getMin() → 4, 4 (getMin does not remove)")
        void testGetMinDoesNotRemove() {
            minStack.push(4);
            assertEquals(4, minStack.getMin());
            assertEquals(4, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MAX_VALUE), push(MIN_VALUE), push(MAX_VALUE) → MIN_VALUE (int extremes)")
        void testGetMinIntExtremes() {
            pushAll(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            assertEquals(Integer.MIN_VALUE, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: push(MAX_VALUE), push(MAX_VALUE - 1) → MAX_VALUE - 1 (near upper bound)")
        void testGetMinNearUpperBound() {
            pushAll(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
            assertEquals(Integer.MAX_VALUE - 1, minStack.getMin());
        }

        @Test
        @DisplayName("Scenario: 29,998 descending pushes (29,997..0) → min 0 (max call count)")
        void testGetMinMaxCalls() {
            for (int i = 0; i < 29_998; i++) {
                minStack.push(29_997 - i);
            }
            assertEquals(0, minStack.getMin());
        }
    }

    private void pushAll(int... values) {
        for (int value : values) {
            minStack.push(value);
        }
    }
}
