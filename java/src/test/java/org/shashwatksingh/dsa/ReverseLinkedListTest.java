package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.ListNode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("ReverseLinkedList Tests")
class ReverseLinkedListTest {

    private ReverseLinkedList instance;

    @BeforeEach
    void setUp() {
        instance = new ReverseLinkedList();
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
        java.util.List<Integer> values = new java.util.ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionIterative()  — iterative pointer reversal, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionIterative() — iterative pointer reversal")
    class SolutionIterativeTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5] → [5,4,3,2,1]")
        void testLeetCodeExample1() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{1, 2, 3, 4, 5}));
            assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(result));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2] → [2,1]")
        void testLeetCodeExample2() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{1, 2}));
            assertArrayEquals(new int[]{2, 1}, toArray(result));
        }

        @Test
        @DisplayName("LeetCode Example 3: [] → []")
        void testLeetCodeExample3() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{}));
            assertArrayEquals(new int[]{}, toArray(result));
        }

        @Test
        @DisplayName("Single-node list: [5] → [5]")
        void testSingleNodeList() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{5}));
            assertArrayEquals(new int[]{5}, toArray(result));
        }

        @Test
        @DisplayName("All-same values: [7,7,7,7] → [7,7,7,7] (values unchanged, links still reversed)")
        void testAllSameValues() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{7, 7, 7, 7}));
            assertArrayEquals(new int[]{7, 7, 7, 7}, toArray(result));
        }

        @Test
        @DisplayName("Negative and boundary constraint values: [-5000,0,5000] → [5000,0,-5000]")
        void testBoundaryValues() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{-5000, 0, 5000}));
            assertArrayEquals(new int[]{5000, 0, -5000}, toArray(result));
        }

        @Test
        @DisplayName("New tail's next pointer is null after reversal")
        void testNewTailPointsToNull() {
            ListNode result = instance.solutionIterative(fromArray(new int[]{1, 2, 3}));
            assertNull(result.next.next.next);
        }

        @Test
        @DisplayName("Larger list (100 nodes) reverses fully")
        void testLargerList() {
            int n = 100;
            int[] input = new int[n];
            int[] expected = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = i;
                expected[i] = n - 1 - i;
            }
            ListNode result = instance.solutionIterative(fromArray(input));
            assertArrayEquals(expected, toArray(result));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionRecursive()  — recursive reversal, O(n) time, O(n) call-stack space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionRecursive() — recursive reversal")
    class SolutionRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5] → [5,4,3,2,1]")
        void testLeetCodeExample1() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{1, 2, 3, 4, 5}));
            assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(result));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2] → [2,1]")
        void testLeetCodeExample2() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{1, 2}));
            assertArrayEquals(new int[]{2, 1}, toArray(result));
        }

        @Test
        @DisplayName("LeetCode Example 3: [] → []")
        void testLeetCodeExample3() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{}));
            assertArrayEquals(new int[]{}, toArray(result));
        }

        @Test
        @DisplayName("Single-node list: [5] → [5]")
        void testSingleNodeList() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{5}));
            assertArrayEquals(new int[]{5}, toArray(result));
        }

        @Test
        @DisplayName("All-same values: [7,7,7,7] → [7,7,7,7] (values unchanged, links still reversed)")
        void testAllSameValues() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{7, 7, 7, 7}));
            assertArrayEquals(new int[]{7, 7, 7, 7}, toArray(result));
        }

        @Test
        @DisplayName("Negative and boundary constraint values: [-5000,0,5000] → [5000,0,-5000]")
        void testBoundaryValues() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{-5000, 0, 5000}));
            assertArrayEquals(new int[]{5000, 0, -5000}, toArray(result));
        }

        @Test
        @DisplayName("New tail's next pointer is null after reversal")
        void testNewTailPointsToNull() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{1, 2, 3}));
            assertNull(result.next.next.next);
        }

        @Test
        @DisplayName("Three-node list: [1,2,3] → [3,2,1]")
        void testThreeNodeList() {
            ListNode result = instance.solutionRecursive(fromArray(new int[]{1, 2, 3}));
            assertArrayEquals(new int[]{3, 2, 1}, toArray(result));
        }
    }
}
