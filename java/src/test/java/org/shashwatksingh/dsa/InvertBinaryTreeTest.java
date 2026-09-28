package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Invert Binary Tree Tests")
class InvertBinaryTreeTest {

    private InvertBinaryTree instance;

    @BeforeEach
    void setUp() {
        instance = new InvertBinaryTree();
    }

    // Canonical string form of a tree — used for structural + value comparison via assertEquals.
    private static String serialize(TreeNode node) {
        if (node == null) return "null";
        return node.val + "(" + serialize(node.left) + "," + serialize(node.right) + ")";
    }

    private static TreeNode leftSkewedChain(int n, int startVal) {
        TreeNode head = null;
        for (int i = n - 1; i >= 0; i--) {
            head = new TreeNode(startVal + i, head, null);
        }
        return head;
    }

    private static TreeNode rightSkewedChain(int n, int startVal) {
        TreeNode head = null;
        for (int i = n - 1; i >= 0; i--) {
            head = new TreeNode(startVal + i, null, head);
        }
        return head;
    }

    // ═══════════════════════════════════════════════════════════
    //  invertTreeRecursive()  — ⚠️ BUGGY — missing null base case, crashes on every input
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("invertTreeRecursive() — ⚠️ BUGGY — no null check before dereferencing root, crashes on any tree")
    class InvertTreeRecursiveTests {

        @Test
        @DisplayName("BUG: empty tree (root=null) → expected null but throws NullPointerException")
        void testEmptyTreeCrashes() {
            assertThrows(NullPointerException.class, () -> instance.invertTreeRecursive(null));
        }

        @Test
        @DisplayName("BUG: minimum-size input [1] → expected [1] but throws NullPointerException "
                + "(recursing into the null child has no base case)")
        void testSingleNodeCrashes() {
            TreeNode root = new TreeNode(1);
            assertThrows(NullPointerException.class, () -> instance.invertTreeRecursive(root));
        }

        @Test
        @DisplayName("BUG: LeetCode Example root=[4,2,7,1,3,6,9] → expected [4,7,2,9,6,3,1] but throws NullPointerException")
        void testLeetCodeExampleCrashes() {
            TreeNode root = new TreeNode(4,
                    new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                    new TreeNode(7, new TreeNode(6), new TreeNode(9)));
            assertThrows(NullPointerException.class, () -> instance.invertTreeRecursive(root));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  invertTreeIterative()  — ⚠️ BUGGY on empty tree only — level-order swap otherwise correct
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("invertTreeIterative() — level-order swap; ⚠️ crashes only when root itself is null")
    class InvertTreeIterativeTests {

        @Test
        @DisplayName("BUG: empty tree (root=null) → expected null but throws NullPointerException")
        void testEmptyTreeCrashes() {
            assertThrows(NullPointerException.class, () -> instance.invertTreeIterative(null));
        }

        @Test
        @DisplayName("Minimum-size input: [1] → [1] (no children to swap)")
        void testSingleNodeUnchanged() {
            TreeNode root = new TreeNode(1);
            assertEquals(serialize(new TreeNode(1)), serialize(instance.invertTreeIterative(root)));
        }

        @Test
        @DisplayName("LeetCode Example: root=[4,2,7,1,3,6,9] → [4,7,2,9,6,3,1]")
        void testLeetCodeExample() {
            TreeNode input = new TreeNode(4,
                    new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                    new TreeNode(7, new TreeNode(6), new TreeNode(9)));
            TreeNode expected = new TreeNode(4,
                    new TreeNode(7, new TreeNode(9), new TreeNode(6)),
                    new TreeNode(2, new TreeNode(3), new TreeNode(1)));
            assertEquals(serialize(expected), serialize(instance.invertTreeIterative(input)));
        }

        @Test
        @DisplayName("Left-skewed chain becomes right-skewed: [1,2,.,3,.,4] mirrored to the other side")
        void testLeftSkewedChainBecomesRightSkewed() {
            TreeNode input = leftSkewedChain(4, 1);
            TreeNode expected = rightSkewedChain(4, 1);
            assertEquals(serialize(expected), serialize(instance.invertTreeIterative(input)));
        }

        @Test
        @DisplayName("Inverting twice restores the original tree")
        void testDoubleInvertRestoresOriginal() {
            TreeNode working = new TreeNode(4,
                    new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                    new TreeNode(7, new TreeNode(6), new TreeNode(9)));
            TreeNode originalClone = new TreeNode(4,
                    new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                    new TreeNode(7, new TreeNode(6), new TreeNode(9)));

            TreeNode invertedOnce = instance.invertTreeIterative(working);
            TreeNode invertedTwice = instance.invertTreeIterative(invertedOnce);

            assertEquals(serialize(originalClone), serialize(invertedTwice));
        }

        @Test
        @DisplayName("Max constraint boundary: 100-node left-skewed chain inverts to a 100-node right-skewed chain")
        void testMaxConstraintBoundaryValues() {
            TreeNode input = leftSkewedChain(100, -100);
            TreeNode expected = rightSkewedChain(100, -100);
            assertEquals(serialize(expected), serialize(instance.invertTreeIterative(input)));
        }
    }
}
