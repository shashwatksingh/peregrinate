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

@DisplayName("Intersection of Two Linked Lists Tests")
class IntersectionofTwoLinkedListsTest {

    private IntersectionofTwoLinkedLists instance;

    @BeforeEach
    void setUp() {
        instance = new IntersectionofTwoLinkedLists();
    }

    private record Lists(ListNode headA, ListNode headB, ListNode expected) {}

    /**
     * Builds list A = prefixA + shared and list B = prefixB + shared, where the shared tail is
     * the same set of node objects. An empty shared tail means the lists do not intersect
     * (expected = null).
     */
    private static Lists build(int[] prefixA, int[] prefixB, int[] shared) {
        ListNode sharedHead = chain(shared, null);
        ListNode headA = chain(prefixA, sharedHead);
        ListNode headB = chain(prefixB, sharedHead);
        return new Lists(headA, headB, sharedHead);
    }

    private static ListNode chain(int[] values, ListNode tail) {
        ListNode head = tail;
        for (int i = values.length - 1; i >= 0; i--) {
            head = new ListNode(values[i], head);
        }
        return head;
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
    //  getIntersectionNodeSetMethod()  — two lockstep HashSets, O(m+n) time, O(m+n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getIntersectionNodeSetMethod() — two lockstep HashSets, O(m+n) time, O(m+n) space")
    class GetIntersectionNodeSetMethodTests {

        @Test
        @DisplayName("LeetCode Example 1: A=[4,1,8,4,5], B=[5,6,1,8,4,5] → intersects at node 8")
        void testLeetCodeExample1() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 2: A=[1,9,1,2,4], B=[3,2,4] → intersects at node 2")
        void testLeetCodeExample2() {
            Lists l = build(new int[]{1,9,1}, new int[]{3}, new int[]{2,4});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 3: A=[2,6,4], B=[1,5] → null")
        void testLeetCodeExample3() {
            Lists l = build(new int[]{2,6,4}, new int[]{1,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single node shared by both lists: A=[1], B=[1] (same node) → that node")
        void testSingleSharedNode() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single nodes, equal values but distinct nodes: [1] vs [1] → null")
        void testSingleDistinctEqualNodes() {
            Lists l = build(new int[]{1}, new int[]{1}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Both heads are the same node: [1,2,3] → head")
        void testSameHead() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1,2,3});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at the last node: A=[1,2,4], B=[3,4] → last node")
        void testIntersectAtLastNode() {
            Lists l = build(new int[]{1,2}, new int[]{3}, new int[]{4});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of A, B longer: A=[9,9], B=[1,2,3,9,9] → head of A")
        void testIntersectAtHeadOfA() {
            Lists l = build(new int[]{}, new int[]{1,2,3}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of B, A longer: A=[1,2,3,9,9], B=[9,9] → head of B")
        void testIntersectAtHeadOfB() {
            Lists l = build(new int[]{1,2,3}, new int[]{}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Equal-length prefixes: A=[1,2,5,6], B=[3,4,5,6] → node 5")
        void testEqualLengthPrefixes() {
            Lists l = build(new int[]{1,2}, new int[]{3,4}, new int[]{5,6});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, intersecting: A=[7,7,7,7,7], B=[7,7,7] → node at index 3 of A")
        void testAllSameValuesIntersecting() {
            Lists l = build(new int[]{7,7,7}, new int[]{7}, new int[]{7,7});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, no intersection: A=[7,7,7], B=[7,7] → null")
        void testAllSameValuesNoIntersection() {
            Lists l = build(new int[]{7,7,7}, new int[]{7,7}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Different lengths, no intersection: A=[1], B=[1,2,3,4,5] → null")
        void testDifferentLengthsNoIntersection() {
            Lists l = build(new int[]{1}, new int[]{1,2,3,4,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Boundary values: A=[1,100000,5], B=[100000,5] (shared tail) → node 100000")
        void testBoundaryValues() {
            Lists l = build(new int[]{1}, new int[]{}, new int[]{100000,5});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (A=20000, B=15000 nodes, sharing last 15000) → first shared node")
        void testMaxSizeIntersecting() {
            Lists l = build(java.util.stream.IntStream.range(1, 5001).toArray(), new int[]{}, java.util.stream.IntStream.range(1, 15001).toArray());
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (30000 + 30000 nodes) with no intersection → null")
        void testMaxSizeNoIntersection() {
            Lists l = build(java.util.stream.IntStream.range(1, 30001).toArray(), java.util.stream.IntStream.range(1, 30001).toArray(), new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethod(l.headA, l.headB));
        }

        @Test
        @DisplayName("Lists keep their original structure after the call: Example 1 → A=[4,1,8,4,5], B=[5,6,1,8,4,5]")
        void testStructurePreserved() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            instance.getIntersectionNodeSetMethod(l.headA, l.headB);
            assertEquals(Arrays.toString(new int[]{4,1,8,4,5}), Arrays.toString(toArray(l.headA)));
            assertEquals(Arrays.toString(new int[]{5,6,1,8,4,5}), Arrays.toString(toArray(l.headB)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  getIntersectionNodeSetMethodOptimized()  — single HashSet of A's nodes, O(m+n) time, O(m) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getIntersectionNodeSetMethodOptimized() — single HashSet of A's nodes, O(m+n) time, O(m) space")
    class GetIntersectionNodeSetMethodOptimizedTests {

        @Test
        @DisplayName("LeetCode Example 1: A=[4,1,8,4,5], B=[5,6,1,8,4,5] → intersects at node 8")
        void testLeetCodeExample1() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 2: A=[1,9,1,2,4], B=[3,2,4] → intersects at node 2")
        void testLeetCodeExample2() {
            Lists l = build(new int[]{1,9,1}, new int[]{3}, new int[]{2,4});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 3: A=[2,6,4], B=[1,5] → null")
        void testLeetCodeExample3() {
            Lists l = build(new int[]{2,6,4}, new int[]{1,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single node shared by both lists: A=[1], B=[1] (same node) → that node")
        void testSingleSharedNode() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single nodes, equal values but distinct nodes: [1] vs [1] → null")
        void testSingleDistinctEqualNodes() {
            Lists l = build(new int[]{1}, new int[]{1}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Both heads are the same node: [1,2,3] → head")
        void testSameHead() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1,2,3});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at the last node: A=[1,2,4], B=[3,4] → last node")
        void testIntersectAtLastNode() {
            Lists l = build(new int[]{1,2}, new int[]{3}, new int[]{4});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of A, B longer: A=[9,9], B=[1,2,3,9,9] → head of A")
        void testIntersectAtHeadOfA() {
            Lists l = build(new int[]{}, new int[]{1,2,3}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of B, A longer: A=[1,2,3,9,9], B=[9,9] → head of B")
        void testIntersectAtHeadOfB() {
            Lists l = build(new int[]{1,2,3}, new int[]{}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Equal-length prefixes: A=[1,2,5,6], B=[3,4,5,6] → node 5")
        void testEqualLengthPrefixes() {
            Lists l = build(new int[]{1,2}, new int[]{3,4}, new int[]{5,6});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, intersecting: A=[7,7,7,7,7], B=[7,7,7] → node at index 3 of A")
        void testAllSameValuesIntersecting() {
            Lists l = build(new int[]{7,7,7}, new int[]{7}, new int[]{7,7});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, no intersection: A=[7,7,7], B=[7,7] → null")
        void testAllSameValuesNoIntersection() {
            Lists l = build(new int[]{7,7,7}, new int[]{7,7}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Different lengths, no intersection: A=[1], B=[1,2,3,4,5] → null")
        void testDifferentLengthsNoIntersection() {
            Lists l = build(new int[]{1}, new int[]{1,2,3,4,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Boundary values: A=[1,100000,5], B=[100000,5] (shared tail) → node 100000")
        void testBoundaryValues() {
            Lists l = build(new int[]{1}, new int[]{}, new int[]{100000,5});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (A=20000, B=15000 nodes, sharing last 15000) → first shared node")
        void testMaxSizeIntersecting() {
            Lists l = build(java.util.stream.IntStream.range(1, 5001).toArray(), new int[]{}, java.util.stream.IntStream.range(1, 15001).toArray());
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (30000 + 30000 nodes) with no intersection → null")
        void testMaxSizeNoIntersection() {
            Lists l = build(java.util.stream.IntStream.range(1, 30001).toArray(), java.util.stream.IntStream.range(1, 30001).toArray(), new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB));
        }

        @Test
        @DisplayName("Lists keep their original structure after the call: Example 1 → A=[4,1,8,4,5], B=[5,6,1,8,4,5]")
        void testStructurePreserved() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            instance.getIntersectionNodeSetMethodOptimized(l.headA, l.headB);
            assertEquals(Arrays.toString(new int[]{4,1,8,4,5}), Arrays.toString(toArray(l.headA)));
            assertEquals(Arrays.toString(new int[]{5,6,1,8,4,5}), Arrays.toString(toArray(l.headB)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  getIntersectionNodeTwoPointer()  — two-pointer list switching, O(m+n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getIntersectionNodeTwoPointer() — two-pointer list switching, O(m+n) time, O(1) space")
    class GetIntersectionNodeTwoPointerTests {

        @Test
        @DisplayName("LeetCode Example 1: A=[4,1,8,4,5], B=[5,6,1,8,4,5] → intersects at node 8")
        void testLeetCodeExample1() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 2: A=[1,9,1,2,4], B=[3,2,4] → intersects at node 2")
        void testLeetCodeExample2() {
            Lists l = build(new int[]{1,9,1}, new int[]{3}, new int[]{2,4});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("LeetCode Example 3: A=[2,6,4], B=[1,5] → null")
        void testLeetCodeExample3() {
            Lists l = build(new int[]{2,6,4}, new int[]{1,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single node shared by both lists: A=[1], B=[1] (same node) → that node")
        void testSingleSharedNode() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Single nodes, equal values but distinct nodes: [1] vs [1] → null")
        void testSingleDistinctEqualNodes() {
            Lists l = build(new int[]{1}, new int[]{1}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Both heads are the same node: [1,2,3] → head")
        void testSameHead() {
            Lists l = build(new int[]{}, new int[]{}, new int[]{1,2,3});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at the last node: A=[1,2,4], B=[3,4] → last node")
        void testIntersectAtLastNode() {
            Lists l = build(new int[]{1,2}, new int[]{3}, new int[]{4});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of A, B longer: A=[9,9], B=[1,2,3,9,9] → head of A")
        void testIntersectAtHeadOfA() {
            Lists l = build(new int[]{}, new int[]{1,2,3}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Intersect at head of B, A longer: A=[1,2,3,9,9], B=[9,9] → head of B")
        void testIntersectAtHeadOfB() {
            Lists l = build(new int[]{1,2,3}, new int[]{}, new int[]{9,9});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Equal-length prefixes: A=[1,2,5,6], B=[3,4,5,6] → node 5")
        void testEqualLengthPrefixes() {
            Lists l = build(new int[]{1,2}, new int[]{3,4}, new int[]{5,6});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, intersecting: A=[7,7,7,7,7], B=[7,7,7] → node at index 3 of A")
        void testAllSameValuesIntersecting() {
            Lists l = build(new int[]{7,7,7}, new int[]{7}, new int[]{7,7});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("All-same values, no intersection: A=[7,7,7], B=[7,7] → null")
        void testAllSameValuesNoIntersection() {
            Lists l = build(new int[]{7,7,7}, new int[]{7,7}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Different lengths, no intersection: A=[1], B=[1,2,3,4,5] → null")
        void testDifferentLengthsNoIntersection() {
            Lists l = build(new int[]{1}, new int[]{1,2,3,4,5}, new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Boundary values: A=[1,100000,5], B=[100000,5] (shared tail) → node 100000")
        void testBoundaryValues() {
            Lists l = build(new int[]{1}, new int[]{}, new int[]{100000,5});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (A=20000, B=15000 nodes, sharing last 15000) → first shared node")
        void testMaxSizeIntersecting() {
            Lists l = build(java.util.stream.IntStream.range(1, 5001).toArray(), new int[]{}, java.util.stream.IntStream.range(1, 15001).toArray());
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Max size (30000 + 30000 nodes) with no intersection → null")
        void testMaxSizeNoIntersection() {
            Lists l = build(java.util.stream.IntStream.range(1, 30001).toArray(), java.util.stream.IntStream.range(1, 30001).toArray(), new int[]{});
            assertEquals(l.expected, instance.getIntersectionNodeTwoPointer(l.headA, l.headB));
        }

        @Test
        @DisplayName("Lists keep their original structure after the call: Example 1 → A=[4,1,8,4,5], B=[5,6,1,8,4,5]")
        void testStructurePreserved() {
            Lists l = build(new int[]{4,1}, new int[]{5,6,1}, new int[]{8,4,5});
            instance.getIntersectionNodeTwoPointer(l.headA, l.headB);
            assertEquals(Arrays.toString(new int[]{4,1,8,4,5}), Arrays.toString(toArray(l.headA)));
            assertEquals(Arrays.toString(new int[]{5,6,1,8,4,5}), Arrays.toString(toArray(l.headB)));
        }
    }
}
