package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Same Tree Tests")
class SameTreeTest {

    private SameTree instance;

    @BeforeEach
    void setUp() {
        instance = new SameTree();
    }

    // Right-skewed chain of n nodes with sequential values [startVal, startVal+n-1].
    private static TreeNode rightSkewedChain(int n, int startVal) {
        TreeNode head = null;
        for (int i = n - 1; i >= 0; i--) {
            head = new TreeNode(startVal + i, null, head);
        }
        return head;
    }

    // ═══════════════════════════════════════════════════════════
    //  isSameTreeRecursive()  — recursive value + structure comparison
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isSameTreeRecursive() — recursive value + structure comparison")
    class IsSameTreeRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: p=[1,2,3], q=[1,2,3] → true")
        void testLeetCodeExample1() {
            TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
            TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
            assertEquals(true, instance.isSameTreeRecursive(p, q));
        }

        @Test
        @DisplayName("LeetCode Example 2: p=[1,2], q=[1,null,2] → false (same values, mirrored structure)")
        void testLeetCodeExample2StructuralMismatch() {
            TreeNode p = new TreeNode(1, new TreeNode(2), null);
            TreeNode q = new TreeNode(1, null, new TreeNode(2));
            assertEquals(false, instance.isSameTreeRecursive(p, q));
        }

        @Test
        @DisplayName("LeetCode Example 3: p=[1,2,1], q=[1,1,2] → false (values swapped at children)")
        void testLeetCodeExample3ValueMismatch() {
            TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(1));
            TreeNode q = new TreeNode(1, new TreeNode(1), new TreeNode(2));
            assertEquals(false, instance.isSameTreeRecursive(p, q));
        }

        @Test
        @DisplayName("Both empty trees: p=null, q=null → true")
        void testBothEmptyTrees() {
            assertEquals(true, instance.isSameTreeRecursive(null, null));
        }

        @Test
        @DisplayName("One empty, one non-empty: p=null, q=[1] → false")
        void testOneEmptyOneNonEmpty() {
            assertEquals(false, instance.isSameTreeRecursive(null, new TreeNode(1)));
        }

        @Test
        @DisplayName("Minimum-size input, same value: p=[5], q=[5] → true")
        void testSingleNodeSameValue() {
            assertEquals(true, instance.isSameTreeRecursive(new TreeNode(5), new TreeNode(5)));
        }

        @Test
        @DisplayName("Minimum-size input, different value: p=[5], q=[6] → false")
        void testSingleNodeDifferentValue() {
            assertEquals(false, instance.isSameTreeRecursive(new TreeNode(5), new TreeNode(6)));
        }

        @Test
        @DisplayName("Deep value mismatch: identical 10-node chains except the last value → false")
        void testDeepValueMismatch() {
            TreeNode p = rightSkewedChain(10, 1);
            TreeNode q = rightSkewedChain(10, 1);
            TreeNode lastQ = q;
            while (lastQ.right != null) lastQ = lastQ.right;
            lastQ.val = -1;
            assertEquals(false, instance.isSameTreeRecursive(p, q));
        }

        @Test
        @DisplayName("Max constraint boundary: identical 100-node chains → true")
        void testMaxConstraintBoundaryIdenticalChains() {
            TreeNode p = rightSkewedChain(100, -10_000);
            TreeNode q = rightSkewedChain(100, -10_000);
            assertEquals(true, instance.isSameTreeRecursive(p, q));
        }

        @Test
        @DisplayName("Max constraint boundary: 100-node chains differing only at the last node → false")
        void testMaxConstraintBoundaryDiffersAtLastNode() {
            TreeNode p = rightSkewedChain(100, -10_000);
            TreeNode q = rightSkewedChain(100, -10_000);
            TreeNode lastQ = q;
            while (lastQ.right != null) lastQ = lastQ.right;
            lastQ.val = 10_000;
            assertEquals(false, instance.isSameTreeRecursive(p, q));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  isSameTreeIterative()  — level-order (BFS) value + structure comparison
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isSameTreeIterative() — level-order (BFS) value + structure comparison")
    class IsSameTreeIterativeTests {

        @Test
        @DisplayName("LeetCode Example 1: p=[1,2,3], q=[1,2,3] → true")
        void testLeetCodeExample1() {
            TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
            TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
            assertEquals(true, instance.isSameTreeIterative(p, q));
        }

        @Test
        @DisplayName("LeetCode Example 2: p=[1,2], q=[1,null,2] → false (same values, mirrored structure)")
        void testLeetCodeExample2StructuralMismatch() {
            TreeNode p = new TreeNode(1, new TreeNode(2), null);
            TreeNode q = new TreeNode(1, null, new TreeNode(2));
            assertEquals(false, instance.isSameTreeIterative(p, q));
        }

        @Test
        @DisplayName("LeetCode Example 3: p=[1,2,1], q=[1,1,2] → false (values swapped at children)")
        void testLeetCodeExample3ValueMismatch() {
            TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(1));
            TreeNode q = new TreeNode(1, new TreeNode(1), new TreeNode(2));
            assertEquals(false, instance.isSameTreeIterative(p, q));
        }

        @Test
        @DisplayName("Both empty trees: p=null, q=null → true")
        void testBothEmptyTrees() {
            assertEquals(true, instance.isSameTreeIterative(null, null));
        }

        @Test
        @DisplayName("One empty, one non-empty: p=null, q=[1] → false")
        void testOneEmptyOneNonEmpty() {
            assertEquals(false, instance.isSameTreeIterative(null, new TreeNode(1)));
        }

        @Test
        @DisplayName("Minimum-size input, same value: p=[5], q=[5] → true")
        void testSingleNodeSameValue() {
            assertEquals(true, instance.isSameTreeIterative(new TreeNode(5), new TreeNode(5)));
        }

        @Test
        @DisplayName("Minimum-size input, different value: p=[5], q=[6] → false")
        void testSingleNodeDifferentValue() {
            assertEquals(false, instance.isSameTreeIterative(new TreeNode(5), new TreeNode(6)));
        }

        @Test
        @DisplayName("Deep value mismatch: identical 10-node chains except the last value → false")
        void testDeepValueMismatch() {
            TreeNode p = rightSkewedChain(10, 1);
            TreeNode q = rightSkewedChain(10, 1);
            TreeNode lastQ = q;
            while (lastQ.right != null) lastQ = lastQ.right;
            lastQ.val = -1;
            assertEquals(false, instance.isSameTreeIterative(p, q));
        }

        @Test
        @DisplayName("Max constraint boundary: identical 100-node chains → true")
        void testMaxConstraintBoundaryIdenticalChains() {
            TreeNode p = rightSkewedChain(100, -10_000);
            TreeNode q = rightSkewedChain(100, -10_000);
            assertEquals(true, instance.isSameTreeIterative(p, q));
        }

        @Test
        @DisplayName("Max constraint boundary: 100-node chains differing only at the last node → false")
        void testMaxConstraintBoundaryDiffersAtLastNode() {
            TreeNode p = rightSkewedChain(100, -10_000);
            TreeNode q = rightSkewedChain(100, -10_000);
            TreeNode lastQ = q;
            while (lastQ.right != null) lastQ = lastQ.right;
            lastQ.val = 10_000;
            assertEquals(false, instance.isSameTreeIterative(p, q));
        }
    }
}
