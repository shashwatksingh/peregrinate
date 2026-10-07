package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Linked List Cycle Tests")
class LinkedListCycleTest {

    private LinkedListCycle instance;

    @BeforeEach
    void setUp() {
        instance = new LinkedListCycle();
    }

    /** Builds a list from values; if pos >= 0 the tail's next is linked to the node at index pos. */
    private static ListNode fromArray(int[] values, int pos) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        ListNode cycleTarget = null;
        for (int i = 0; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
            if (i == pos) cycleTarget = curr;
        }
        if (pos >= 0) curr.next = cycleTarget;
        return dummy.next;
    }

    // ═══════════════════════════════════════════════════════════
    //  hasCycleUsingHashSets()  — HashSet of visited nodes, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("hasCycleUsingHashSets() — HashSet of visited nodes, O(n) time, O(n) space")
    class HasCycleUsingHashSetsTests {

        @Test
        @DisplayName("LeetCode Example 1: [3,2,0,-4], pos=1 → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{3,2,0,-4}, 1)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2], pos=0 → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2}, 0)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1], pos=-1 → false")
        void testLeetCodeExample3() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{1}, -1)));
        }

        @Test
        @DisplayName("Empty list: [] → false")
        void testEmptyList() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{}, -1)));
        }

        @Test
        @DisplayName("Single node pointing to itself: [1], pos=0 → true")
        void testSingleNodeSelfLoop() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1}, 0)));
        }

        @Test
        @DisplayName("Two nodes, no cycle: [1,2], pos=-1 → false")
        void testTwoNodesNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2}, -1)));
        }

        @Test
        @DisplayName("Two nodes, tail points to itself: [1,2], pos=1 → true")
        void testTwoNodesTailSelfLoop() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2}, 1)));
        }

        @Test
        @DisplayName("All-same values, no cycle: [7,7,7,7], pos=-1 → false (equal values are not a cycle)")
        void testAllSameValuesNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{7,7,7,7}, -1)));
        }

        @Test
        @DisplayName("All-same values, cycle: [7,7,7,7], pos=2 → true")
        void testAllSameValuesWithCycle() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{7,7,7,7}, 2)));
        }

        @Test
        @DisplayName("Tail connects to head: [1,2,3,4,5], pos=0 → true")
        void testTailToHead() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2,3,4,5}, 0)));
        }

        @Test
        @DisplayName("Tail connects to itself: [1,2,3,4,5], pos=4 → true")
        void testTailToTail() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2,3,4,5}, 4)));
        }

        @Test
        @DisplayName("Tail connects to middle: [1,2,3,4,5], pos=2 → true")
        void testTailToMiddle() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2,3,4,5}, 2)));
        }

        @Test
        @DisplayName("Odd-length list, no cycle: [1,2,3] → false")
        void testOddLengthNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2,3}, -1)));
        }

        @Test
        @DisplayName("Even-length list, no cycle: [1,2,3,4] → false")
        void testEvenLengthNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{1,2,3,4}, -1)));
        }

        @Test
        @DisplayName("Boundary values: [-100000,100000,0], pos=1 → true")
        void testBoundaryValuesWithCycle() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(new int[]{-100000,100000,0}, 1)));
        }

        @Test
        @DisplayName("Boundary values, no cycle: [-100000,100000,0] → false")
        void testBoundaryValuesNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(new int[]{-100000,100000,0}, -1)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), no cycle → false")
        void testMaxSizeNoCycle() {
            assertEquals(false, instance.hasCycleUsingHashSets(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), -1)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), tail to head → true")
        void testMaxSizeTailToHead() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), 0)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), tail to last node → true")
        void testMaxSizeTailToSelf() {
            assertEquals(true, instance.hasCycleUsingHashSets(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), 9999)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  hasCycleFloyds()  — Floyd's tortoise and hare, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("hasCycleFloyds() — Floyd's tortoise and hare, O(n) time, O(1) space")
    class HasCycleFloydsTests {

        @Test
        @DisplayName("LeetCode Example 1: [3,2,0,-4], pos=1 → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{3,2,0,-4}, 1)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2], pos=0 → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1,2}, 0)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1], pos=-1 → false")
        void testLeetCodeExample3() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{1}, -1)));
        }

        @Test
        @DisplayName("Empty list: [] → false")
        void testEmptyList() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{}, -1)));
        }

        @Test
        @DisplayName("Single node pointing to itself: [1], pos=0 → true")
        void testSingleNodeSelfLoop() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1}, 0)));
        }

        @Test
        @DisplayName("Two nodes, no cycle: [1,2], pos=-1 → false")
        void testTwoNodesNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{1,2}, -1)));
        }

        @Test
        @DisplayName("Two nodes, tail points to itself: [1,2], pos=1 → true")
        void testTwoNodesTailSelfLoop() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1,2}, 1)));
        }

        @Test
        @DisplayName("All-same values, no cycle: [7,7,7,7], pos=-1 → false (equal values are not a cycle)")
        void testAllSameValuesNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{7,7,7,7}, -1)));
        }

        @Test
        @DisplayName("All-same values, cycle: [7,7,7,7], pos=2 → true")
        void testAllSameValuesWithCycle() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{7,7,7,7}, 2)));
        }

        @Test
        @DisplayName("Tail connects to head: [1,2,3,4,5], pos=0 → true")
        void testTailToHead() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1,2,3,4,5}, 0)));
        }

        @Test
        @DisplayName("Tail connects to itself: [1,2,3,4,5], pos=4 → true")
        void testTailToTail() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1,2,3,4,5}, 4)));
        }

        @Test
        @DisplayName("Tail connects to middle: [1,2,3,4,5], pos=2 → true")
        void testTailToMiddle() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{1,2,3,4,5}, 2)));
        }

        @Test
        @DisplayName("Odd-length list, no cycle: [1,2,3] → false")
        void testOddLengthNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{1,2,3}, -1)));
        }

        @Test
        @DisplayName("Even-length list, no cycle: [1,2,3,4] → false")
        void testEvenLengthNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{1,2,3,4}, -1)));
        }

        @Test
        @DisplayName("Boundary values: [-100000,100000,0], pos=1 → true")
        void testBoundaryValuesWithCycle() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(new int[]{-100000,100000,0}, 1)));
        }

        @Test
        @DisplayName("Boundary values, no cycle: [-100000,100000,0] → false")
        void testBoundaryValuesNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(new int[]{-100000,100000,0}, -1)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), no cycle → false")
        void testMaxSizeNoCycle() {
            assertEquals(false, instance.hasCycleFloyds(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), -1)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), tail to head → true")
        void testMaxSizeTailToHead() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), 0)));
        }

        @Test
        @DisplayName("Max size (10000 nodes), tail to last node → true")
        void testMaxSizeTailToSelf() {
            assertEquals(true, instance.hasCycleFloyds(fromArray(java.util.stream.IntStream.range(0, 10000).toArray(), 9999)));
        }
    }
}
