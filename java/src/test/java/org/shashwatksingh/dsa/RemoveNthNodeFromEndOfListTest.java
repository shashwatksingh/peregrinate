package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Remove Nth Node From End of List Tests")
class RemoveNthNodeFromEndOfListTest {

    private RemoveNthNodeFromEndOfList instance;

    @BeforeEach
    void setUp() {
        instance = new RemoveNthNodeFromEndOfList();
    }

    private static ListNode fromArray(int[] values) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static int[] toArray(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  solution2Pass()  — remove nth node from end, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution2Pass() — remove nth node from end")
    class Solution2PassTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5], n=2 → [1,2,3,5]")
        void testLeetCodeExample1() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3,4,5}), 2);
            assertEquals(Arrays.toString(new int[]{1,2,3,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1], n=1 → []")
        void testLeetCodeExample2() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1}), 1);
            assertEquals(Arrays.toString(new int[]{}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2], n=1 → [1]")
        void testLeetCodeExample3() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2}), 1);
            assertEquals(Arrays.toString(new int[]{1}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Two nodes, remove head: [1,2], n=2 → [2]")
        void testTwoNodesRemoveHead() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2}), 2);
            assertEquals(Arrays.toString(new int[]{2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove head of longer list: [1,2,3,4,5], n=5 → [2,3,4,5]")
        void testRemoveHead() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3,4,5}), 5);
            assertEquals(Arrays.toString(new int[]{2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove tail: [1,2,3,4,5], n=1 → [1,2,3,4]")
        void testRemoveTail() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3,4,5}), 1);
            assertEquals(Arrays.toString(new int[]{1,2,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove middle node: [1,2,3], n=2 → [1,3]")
        void testRemoveMiddle() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3}), 2);
            assertEquals(Arrays.toString(new int[]{1,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove second node: [1,2,3,4], n=3 → [1,3,4]")
        void testRemoveSecondNode() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3,4}), 3);
            assertEquals(Arrays.toString(new int[]{1,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Three nodes, remove head: [1,2,3], n=3 → [2,3]")
        void testThreeNodesRemoveHead() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{1,2,3}), 3);
            assertEquals(Arrays.toString(new int[]{2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove 2nd from end: [7,7,7,7], n=2 → [7,7,7]")
        void testAllSameValuesN2() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{7,7,7,7}), 2);
            assertEquals(Arrays.toString(new int[]{7,7,7}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove tail: [5,5], n=1 → [5]")
        void testAllSameValuesN1() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{5,5}), 1);
            assertEquals(Arrays.toString(new int[]{5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Distinct values, remove 3rd from end: [10,20,30,40,50], n=3 → [10,20,40,50]")
        void testDistinctValuesN3() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{10,20,30,40,50}), 3);
            assertEquals(Arrays.toString(new int[]{10,20,40,50}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Boundary values 0 and 100, remove head: [0,100,0,100], n=4 → [100,0,100]")
        void testBoundaryValues() {
            ListNode result = instance.solution2Pass(fromArray(new int[]{0,100,0,100}), 4);
            assertEquals(Arrays.toString(new int[]{100,0,100}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove tail: [0..29], n=1 → [0..29] without value 29")
        void testMaxSizeRemoveTail() {
            ListNode result = instance.solution2Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 1);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 29).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove middle: [0..29], n=15 → [0..29] without value 15")
        void testMaxSizeRemoveMiddle() {
            ListNode result = instance.solution2Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 15);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 15).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove head: [0..29], n=30 → [0..29] without value 0")
        void testMaxSizeRemoveHead() {
            ListNode result = instance.solution2Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 30);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 0).toArray()), Arrays.toString(toArray(result)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solution1Pass()  — one-pass two-pointer removal, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution1Pass() — one-pass two-pointer removal")
    class Solution1PassTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5], n=2 → [1,2,3,5]")
        void testLeetCodeExample1() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3,4,5}), 2);
            assertEquals(Arrays.toString(new int[]{1,2,3,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1], n=1 → []")
        void testLeetCodeExample2() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1}), 1);
            assertEquals(Arrays.toString(new int[]{}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2], n=1 → [1]")
        void testLeetCodeExample3() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2}), 1);
            assertEquals(Arrays.toString(new int[]{1}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Two nodes, remove head: [1,2], n=2 → [2]")
        void testTwoNodesRemoveHead() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2}), 2);
            assertEquals(Arrays.toString(new int[]{2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove head of longer list: [1,2,3,4,5], n=5 → [2,3,4,5]")
        void testRemoveHead() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3,4,5}), 5);
            assertEquals(Arrays.toString(new int[]{2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove tail: [1,2,3,4,5], n=1 → [1,2,3,4]")
        void testRemoveTail() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3,4,5}), 1);
            assertEquals(Arrays.toString(new int[]{1,2,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove middle node: [1,2,3], n=2 → [1,3]")
        void testRemoveMiddle() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3}), 2);
            assertEquals(Arrays.toString(new int[]{1,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove second node: [1,2,3,4], n=3 → [1,3,4]")
        void testRemoveSecondNode() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3,4}), 3);
            assertEquals(Arrays.toString(new int[]{1,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Three nodes, remove head: [1,2,3], n=3 → [2,3]")
        void testThreeNodesRemoveHead() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{1,2,3}), 3);
            assertEquals(Arrays.toString(new int[]{2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove 2nd from end: [7,7,7,7], n=2 → [7,7,7]")
        void testAllSameValuesN2() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{7,7,7,7}), 2);
            assertEquals(Arrays.toString(new int[]{7,7,7}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove tail: [5,5], n=1 → [5]")
        void testAllSameValuesN1() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{5,5}), 1);
            assertEquals(Arrays.toString(new int[]{5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Distinct values, remove 3rd from end: [10,20,30,40,50], n=3 → [10,20,40,50]")
        void testDistinctValuesN3() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{10,20,30,40,50}), 3);
            assertEquals(Arrays.toString(new int[]{10,20,40,50}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Boundary values 0 and 100, remove head: [0,100,0,100], n=4 → [100,0,100]")
        void testBoundaryValues() {
            ListNode result = instance.solution1Pass(fromArray(new int[]{0,100,0,100}), 4);
            assertEquals(Arrays.toString(new int[]{100,0,100}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove tail: [0..29], n=1 → [0..29] without value 29")
        void testMaxSizeRemoveTail() {
            ListNode result = instance.solution1Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 1);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 29).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove middle: [0..29], n=15 → [0..29] without value 15")
        void testMaxSizeRemoveMiddle() {
            ListNode result = instance.solution1Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 15);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 15).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove head: [0..29], n=30 → [0..29] without value 0")
        void testMaxSizeRemoveHead() {
            ListNode result = instance.solution1Pass(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 30);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 0).toArray()), Arrays.toString(toArray(result)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solution2PassWithDummyNode()  — two-pass removal with a dummy head node, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution2PassWithDummyNode() — two-pass removal with a dummy head node")
    class Solution2PassWithDummyNodeTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5], n=2 → [1,2,3,5]")
        void testLeetCodeExample1() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3,4,5}), 2);
            assertEquals(Arrays.toString(new int[]{1,2,3,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1], n=1 → []")
        void testLeetCodeExample2() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1}), 1);
            assertEquals(Arrays.toString(new int[]{}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2], n=1 → [1]")
        void testLeetCodeExample3() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2}), 1);
            assertEquals(Arrays.toString(new int[]{1}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Two nodes, remove head: [1,2], n=2 → [2]")
        void testTwoNodesRemoveHead() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2}), 2);
            assertEquals(Arrays.toString(new int[]{2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove head of longer list: [1,2,3,4,5], n=5 → [2,3,4,5]")
        void testRemoveHead() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3,4,5}), 5);
            assertEquals(Arrays.toString(new int[]{2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove tail: [1,2,3,4,5], n=1 → [1,2,3,4]")
        void testRemoveTail() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3,4,5}), 1);
            assertEquals(Arrays.toString(new int[]{1,2,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove middle node: [1,2,3], n=2 → [1,3]")
        void testRemoveMiddle() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3}), 2);
            assertEquals(Arrays.toString(new int[]{1,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Remove second node: [1,2,3,4], n=3 → [1,3,4]")
        void testRemoveSecondNode() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3,4}), 3);
            assertEquals(Arrays.toString(new int[]{1,3,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Three nodes, remove head: [1,2,3], n=3 → [2,3]")
        void testThreeNodesRemoveHead() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{1,2,3}), 3);
            assertEquals(Arrays.toString(new int[]{2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove 2nd from end: [7,7,7,7], n=2 → [7,7,7]")
        void testAllSameValuesN2() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{7,7,7,7}), 2);
            assertEquals(Arrays.toString(new int[]{7,7,7}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values, remove tail: [5,5], n=1 → [5]")
        void testAllSameValuesN1() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{5,5}), 1);
            assertEquals(Arrays.toString(new int[]{5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Distinct values, remove 3rd from end: [10,20,30,40,50], n=3 → [10,20,40,50]")
        void testDistinctValuesN3() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{10,20,30,40,50}), 3);
            assertEquals(Arrays.toString(new int[]{10,20,40,50}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Boundary values 0 and 100, remove head: [0,100,0,100], n=4 → [100,0,100]")
        void testBoundaryValues() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(new int[]{0,100,0,100}), 4);
            assertEquals(Arrays.toString(new int[]{100,0,100}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove tail: [0..29], n=1 → [0..29] without value 29")
        void testMaxSizeRemoveTail() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 1);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 29).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove middle: [0..29], n=15 → [0..29] without value 15")
        void testMaxSizeRemoveMiddle() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 15);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 15).toArray()), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (30 nodes), remove head: [0..29], n=30 → [0..29] without value 0")
        void testMaxSizeRemoveHead() {
            ListNode result = instance.solution2PassWithDummyNode(fromArray(java.util.stream.IntStream.range(0, 30).toArray()), 30);
            assertEquals(Arrays.toString(java.util.stream.IntStream.range(0, 30).filter(i -> i != 0).toArray()), Arrays.toString(toArray(result)));
        }
    }
}
