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

@DisplayName("Merge Two Sorted Lists Tests")
class MergeTwoSortedListsTest {

    private MergeTwoSortedLists instance;

    @BeforeEach
    void setUp() {
        instance = new MergeTwoSortedLists();
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
    //  mergeTwoLLIterative()  — iterative merge, O(n+m) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("mergeTwoLLIterative() — iterative merge, O(n+m) time, O(1) space")
    class MergeTwoLLIterativeTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,4] + [1,3,4] → [1,1,2,3,4,4]")
        void testLeetCodeExample1() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1,2,4}), fromArray(new int[]{1,3,4}));
            assertEquals(Arrays.toString(new int[]{1,1,2,3,4,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [] + [] → []")
        void testLeetCodeExample2() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{}), fromArray(new int[]{}));
            assertEquals(Arrays.toString(new int[]{}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [] + [0] → [0]")
        void testLeetCodeExample3() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{}), fromArray(new int[]{0}));
            assertEquals(Arrays.toString(new int[]{0}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("First list empty: [] + [1,2,3] → [1,2,3]")
        void testFirstListEmpty() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{}), fromArray(new int[]{1,2,3}));
            assertEquals(Arrays.toString(new int[]{1,2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Second list empty: [1,2,3] + [] → [1,2,3]")
        void testSecondListEmpty() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1,2,3}), fromArray(new int[]{}));
            assertEquals(Arrays.toString(new int[]{1,2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single nodes: [1] + [2] → [1,2]")
        void testSingleNodesInOrder() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1}), fromArray(new int[]{2}));
            assertEquals(Arrays.toString(new int[]{1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single nodes reversed: [2] + [1] → [1,2]")
        void testSingleNodesReversed() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{2}), fromArray(new int[]{1}));
            assertEquals(Arrays.toString(new int[]{1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single node each, equal: [3] + [3] → [3,3]")
        void testSingleNodesEqual() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{3}), fromArray(new int[]{3}));
            assertEquals(Arrays.toString(new int[]{3,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values: [5,5,5] + [5,5] → [5,5,5,5,5]")
        void testAllSameValues() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{5,5,5}), fromArray(new int[]{5,5}));
            assertEquals(Arrays.toString(new int[]{5,5,5,5,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All of list1 smaller: [1,2,3] + [4,5,6] → [1,2,3,4,5,6]")
        void testAllFirstSmaller() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1,2,3}), fromArray(new int[]{4,5,6}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All of list2 smaller: [4,5,6] + [1,2,3] → [1,2,3,4,5,6]")
        void testAllSecondSmaller() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{4,5,6}), fromArray(new int[]{1,2,3}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Interleaved: [1,3,5] + [2,4,6] → [1,2,3,4,5,6]")
        void testInterleaved() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1,3,5}), fromArray(new int[]{2,4,6}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Different lengths: [1] + [2,3,4,5] → [1,2,3,4,5]")
        void testDifferentLengthsShortFirst() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1}), fromArray(new int[]{2,3,4,5}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Different lengths: [2,3,4,5] + [1] → [1,2,3,4,5]")
        void testDifferentLengthsLongFirst() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{2,3,4,5}), fromArray(new int[]{1}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Negative values: [-10,-3,0] + [-5,-1,2] → [-10,-5,-3,-1,0,2]")
        void testNegativeValues() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{-10,-3,0}), fromArray(new int[]{-5,-1,2}));
            assertEquals(Arrays.toString(new int[]{-10,-5,-3,-1,0,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Boundary values: [-100,100] + [-100,100] → [-100,-100,100,100]")
        void testBoundaryValues() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{-100,100}), fromArray(new int[]{-100,100}));
            assertEquals(Arrays.toString(new int[]{-100,-100,100,100}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Duplicates within a list: [1,1,1] + [1,2] → [1,1,1,1,2]")
        void testDuplicatesWithinList() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{1,1,1}), fromArray(new int[]{1,2}));
            assertEquals(Arrays.toString(new int[]{1,1,1,1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (50 + 50 nodes): evens + odds → 0..99")
        void testMaxConstraintSize() {
            ListNode result = instance.mergeTwoLLIterative(fromArray(new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98}), fromArray(new int[]{1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73,75,77,79,81,83,85,87,89,91,93,95,97,99}));
            assertEquals(Arrays.toString(new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99}), Arrays.toString(toArray(result)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  mergeTwoLLRecursive()  — recursive merge, O(n+m) time, O(n+m) call-stack space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("mergeTwoLLRecursive() — recursive merge, O(n+m) time, O(n+m) call-stack space")
    class MergeTwoLLRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,4] + [1,3,4] → [1,1,2,3,4,4]")
        void testLeetCodeExample1() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1,2,4}), fromArray(new int[]{1,3,4}));
            assertEquals(Arrays.toString(new int[]{1,1,2,3,4,4}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [] + [] → []")
        void testLeetCodeExample2() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{}), fromArray(new int[]{}));
            assertEquals(Arrays.toString(new int[]{}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [] + [0] → [0]")
        void testLeetCodeExample3() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{}), fromArray(new int[]{0}));
            assertEquals(Arrays.toString(new int[]{0}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("First list empty: [] + [1,2,3] → [1,2,3]")
        void testFirstListEmpty() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{}), fromArray(new int[]{1,2,3}));
            assertEquals(Arrays.toString(new int[]{1,2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Second list empty: [1,2,3] + [] → [1,2,3]")
        void testSecondListEmpty() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1,2,3}), fromArray(new int[]{}));
            assertEquals(Arrays.toString(new int[]{1,2,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single nodes: [1] + [2] → [1,2]")
        void testSingleNodesInOrder() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1}), fromArray(new int[]{2}));
            assertEquals(Arrays.toString(new int[]{1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single nodes reversed: [2] + [1] → [1,2]")
        void testSingleNodesReversed() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{2}), fromArray(new int[]{1}));
            assertEquals(Arrays.toString(new int[]{1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Single node each, equal: [3] + [3] → [3,3]")
        void testSingleNodesEqual() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{3}), fromArray(new int[]{3}));
            assertEquals(Arrays.toString(new int[]{3,3}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All-same values: [5,5,5] + [5,5] → [5,5,5,5,5]")
        void testAllSameValues() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{5,5,5}), fromArray(new int[]{5,5}));
            assertEquals(Arrays.toString(new int[]{5,5,5,5,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All of list1 smaller: [1,2,3] + [4,5,6] → [1,2,3,4,5,6]")
        void testAllFirstSmaller() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1,2,3}), fromArray(new int[]{4,5,6}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("All of list2 smaller: [4,5,6] + [1,2,3] → [1,2,3,4,5,6]")
        void testAllSecondSmaller() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{4,5,6}), fromArray(new int[]{1,2,3}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Interleaved: [1,3,5] + [2,4,6] → [1,2,3,4,5,6]")
        void testInterleaved() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1,3,5}), fromArray(new int[]{2,4,6}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5,6}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Different lengths: [1] + [2,3,4,5] → [1,2,3,4,5]")
        void testDifferentLengthsShortFirst() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1}), fromArray(new int[]{2,3,4,5}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Different lengths: [2,3,4,5] + [1] → [1,2,3,4,5]")
        void testDifferentLengthsLongFirst() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{2,3,4,5}), fromArray(new int[]{1}));
            assertEquals(Arrays.toString(new int[]{1,2,3,4,5}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Negative values: [-10,-3,0] + [-5,-1,2] → [-10,-5,-3,-1,0,2]")
        void testNegativeValues() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{-10,-3,0}), fromArray(new int[]{-5,-1,2}));
            assertEquals(Arrays.toString(new int[]{-10,-5,-3,-1,0,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Boundary values: [-100,100] + [-100,100] → [-100,-100,100,100]")
        void testBoundaryValues() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{-100,100}), fromArray(new int[]{-100,100}));
            assertEquals(Arrays.toString(new int[]{-100,-100,100,100}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Duplicates within a list: [1,1,1] + [1,2] → [1,1,1,1,2]")
        void testDuplicatesWithinList() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{1,1,1}), fromArray(new int[]{1,2}));
            assertEquals(Arrays.toString(new int[]{1,1,1,1,2}), Arrays.toString(toArray(result)));
        }

        @Test
        @DisplayName("Max size (50 + 50 nodes): evens + odds → 0..99")
        void testMaxConstraintSize() {
            ListNode result = instance.mergeTwoLLRecursive(fromArray(new int[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,94,96,98}), fromArray(new int[]{1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73,75,77,79,81,83,85,87,89,91,93,95,97,99}));
            assertEquals(Arrays.toString(new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99}), Arrays.toString(toArray(result)));
        }
    }
}
