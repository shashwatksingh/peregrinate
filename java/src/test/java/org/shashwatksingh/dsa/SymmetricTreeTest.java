package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
101. Symmetric Tree
Given the root of a binary tree, check whether it is a mirror of itself
(i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
*/
@DisplayName("Symmetric Tree Tests")
class SymmetricTreeTest {

    private SymmetricTree instance;

    @BeforeEach
    void setUp() {
        instance = new SymmetricTree();
    }

    // Exact mirror image of the given subtree (independent copy, swapping left/right at every level).
    private static TreeNode mirrorClone(TreeNode node) {
        if (node == null) return null;
        return new TreeNode(node.val, mirrorClone(node.right), mirrorClone(node.left));
    }

    // A perfect binary tree of the given depth with distinct, constraint-bounded values assigned
    // in pre-order via the shared counter.
    private static TreeNode perfectTreeWithDistinctValues(int depth, int[] counter) {
        if (depth == 0) return null;
        int val = (counter[0]++ % 201) - 100;
        TreeNode node = new TreeNode(val);
        node.left = perfectTreeWithDistinctValues(depth - 1, counter);
        node.right = perfectTreeWithDistinctValues(depth - 1, counter);
        return node;
    }

    // A genuinely symmetric tree of the given depth: root plus a subtree and its exact mirror.
    private static TreeNode buildSymmetricTree(int depth, int rootVal) {
        TreeNode leftSub = perfectTreeWithDistinctValues(depth - 1, new int[]{1});
        TreeNode rightSub = mirrorClone(leftSub);
        return new TreeNode(rootVal, leftSub, rightSub);
    }

    // ═══════════════════════════════════════════════════════════
    //  isSymmetricRecursive()  — recursive mirror comparison
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isSymmetricRecursive() — recursive mirror comparison")
    class IsSymmetricRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: root=[1,2,2,3,4,4,3] → true")
        void testLeetCodeExample1() {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                    new TreeNode(2, new TreeNode(4), new TreeNode(3)));
            assertEquals(true, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: root=[1,2,2,null,3,null,3] → false")
        void testLeetCodeExample2() {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2, null, new TreeNode(3)),
                    new TreeNode(2, null, new TreeNode(3)));
            assertEquals(false, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Minimum-size input: root=[1] → true (single node)")
        void testMinimumSizeInput() {
            assertEquals(true, instance.isSymmetricRecursive(new TreeNode(1)));
        }

        @Test
        @DisplayName("Left child only, no right child: root=[1,2] → false")
        void testLeftChildOnlyIsAsymmetric() {
            TreeNode root = new TreeNode(1, new TreeNode(2), null);
            assertEquals(false, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Right child only, no left child: root=[1,null,2] → false")
        void testRightChildOnlyIsAsymmetric() {
            TreeNode root = new TreeNode(1, null, new TreeNode(2));
            assertEquals(false, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Deep symmetric tree (4 levels, mirrored construction) → true")
        void testDeepSymmetricTree() {
            TreeNode root = buildSymmetricTree(4, 0);
            assertEquals(true, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Deep asymmetric mismatch: one deep leaf value broken → false")
        void testDeepAsymmetricMismatch() {
            TreeNode root = buildSymmetricTree(4, 0);
            TreeNode cursor = root.right;
            while (cursor.left != null) cursor = cursor.left;
            cursor.val += 1;
            assertEquals(false, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Max constraint boundary: 511-node symmetric tree (depth 9) → true")
        void testMaxConstraintBoundarySymmetric() {
            TreeNode root = buildSymmetricTree(9, 0);
            assertEquals(true, instance.isSymmetricRecursive(root));
        }

        @Test
        @DisplayName("Max constraint boundary: 511-node tree with one deep mismatch → false")
        void testMaxConstraintBoundaryAsymmetric() {
            TreeNode root = buildSymmetricTree(9, 0);
            TreeNode cursor = root.right;
            while (cursor.left != null) cursor = cursor.left;
            cursor.val += 1;
            assertEquals(false, instance.isSymmetricRecursive(root));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  isSymmetricIterative()  — deque-based mirror comparison
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isSymmetricIterative() — deque-based mirror comparison")
    class IsSymmetricIterativeTests {

        @Test
        @DisplayName("LeetCode Example 1: root=[1,2,2,3,4,4,3] → true")
        void testLeetCodeExample1() {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                    new TreeNode(2, new TreeNode(4), new TreeNode(3)));
            assertEquals(true, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: root=[1,2,2,null,3,null,3] → false")
        void testLeetCodeExample2() {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2, null, new TreeNode(3)),
                    new TreeNode(2, null, new TreeNode(3)));
            assertEquals(false, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Minimum-size input: root=[1] → true (single node)")
        void testMinimumSizeInput() {
            assertEquals(true, instance.isSymmetricIterative(new TreeNode(1)));
        }

        @Test
        @DisplayName("Left child only, no right child: root=[1,2] → false")
        void testLeftChildOnlyIsAsymmetric() {
            TreeNode root = new TreeNode(1, new TreeNode(2), null);
            assertEquals(false, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Right child only, no left child: root=[1,null,2] → false")
        void testRightChildOnlyIsAsymmetric() {
            TreeNode root = new TreeNode(1, null, new TreeNode(2));
            assertEquals(false, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Deep symmetric tree (4 levels, mirrored construction) → true")
        void testDeepSymmetricTree() {
            TreeNode root = buildSymmetricTree(4, 0);
            assertEquals(true, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Deep asymmetric mismatch: one deep leaf value broken → false")
        void testDeepAsymmetricMismatch() {
            TreeNode root = buildSymmetricTree(4, 0);
            TreeNode cursor = root.right;
            while (cursor.left != null) cursor = cursor.left;
            cursor.val += 1;
            assertEquals(false, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Max constraint boundary: 511-node symmetric tree (depth 9) → true")
        void testMaxConstraintBoundarySymmetric() {
            TreeNode root = buildSymmetricTree(9, 0);
            assertEquals(true, instance.isSymmetricIterative(root));
        }

        @Test
        @DisplayName("Max constraint boundary: 511-node tree with one deep mismatch → false")
        void testMaxConstraintBoundaryAsymmetric() {
            TreeNode root = buildSymmetricTree(9, 0);
            TreeNode cursor = root.right;
            while (cursor.left != null) cursor = cursor.left;
            cursor.val += 1;
            assertEquals(false, instance.isSymmetricIterative(root));
        }
    }
}
