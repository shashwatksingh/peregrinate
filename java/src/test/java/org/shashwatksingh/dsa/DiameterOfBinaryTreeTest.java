package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DiameterOfBinaryTree Tests")
class DiameterOfBinaryTreeTest {

    private DiameterOfBinaryTree instance;

    @BeforeEach
    void setUp() {
        instance = new DiameterOfBinaryTree();
    }

    private static TreeNode leaf(int val) {
        return new TreeNode(val);
    }

    private static TreeNode node(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    // ═══════════════════════════════════════════════════════════
    //  diameterOfBinaryTree()  — DFS height/diameter tracking, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("diameterOfBinaryTree() — DFS height/diameter tracking")
    class DiameterOfBinaryTreeTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5] → 3 (path [4,2,1,3] or [5,2,1,3])")
        void testLeetCodeExample1() {
            TreeNode root = node(1, node(2, leaf(4), leaf(5)), leaf(3));
            assertEquals(3, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2] → 1")
        void testLeetCodeExample2() {
            TreeNode root = node(1, leaf(2), null);
            assertEquals(1, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Single node, no children: [1] → 0")
        void testSingleNode() {
            assertEquals(0, instance.diameterOfBinaryTree(leaf(1)));
        }

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.diameterOfBinaryTree(null));
        }

        @Test
        @DisplayName("All-same values, perfect tree of depth 2 (7 nodes, all val=7) → 4")
        void testAllSameValuesPerfectTree() {
            TreeNode root = node(7,
                    node(7, leaf(7), leaf(7)),
                    node(7, leaf(7), leaf(7)));
            assertEquals(4, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Right-skewed chain, 5 nodes: 1→2→3→4→5 via right children → 4")
        void testRightSkewedChain() {
            TreeNode root = node(1, null,
                    node(2, null,
                            node(3, null,
                                    node(4, null, leaf(5)))));
            assertEquals(4, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Left-skewed chain, 5 nodes: 1→2→3→4→5 via left children → 4")
        void testLeftSkewedChain() {
            TreeNode root = node(1,
                    node(2,
                            node(3,
                                    node(4, leaf(5), null),
                                    null),
                            null),
                    null);
            assertEquals(4, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Perfect binary tree, depth 3 (15 nodes) with branching at every level → 6")
        void testPerfectTreeDeepBranching() {
            TreeNode l4a = node(4, leaf(8), leaf(9));
            TreeNode l4b = node(5, leaf(10), leaf(11));
            TreeNode l4c = node(6, leaf(12), leaf(13));
            TreeNode l4d = node(7, leaf(14), leaf(15));
            TreeNode l1 = node(2, l4a, l4b);
            TreeNode l2 = node(3, l4c, l4d);
            TreeNode root = node(1, l1, l2);
            assertEquals(6, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Node values at min/max constraint boundaries: [-100,100] → 1")
        void testBoundaryNodeValues() {
            TreeNode root = node(-100, leaf(100), null);
            assertEquals(1, instance.diameterOfBinaryTree(root));
        }

        @Test
        @DisplayName("Moderately deep skewed chain (1000 nodes) resolves to full chain length")
        void testModeratelyDeepSkewedChain() {
            int depth = 1000;
            TreeNode current = leaf(depth);
            for (int v = depth - 1; v >= 1; v--) {
                current = node(v, current, null);
            }
            assertEquals(depth - 1, instance.diameterOfBinaryTree(current));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  diameterOfBinaryTreeIteratively()  — iterative post-order via stack + map, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("diameterOfBinaryTreeIteratively() — iterative post-order via stack + map")
    class DiameterOfBinaryTreeIterativelyTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4,5] → 3 (path [4,2,1,3] or [5,2,1,3])")
        void testLeetCodeExample1() {
            TreeNode root = node(1, node(2, leaf(4), leaf(5)), leaf(3));
            assertEquals(3, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2] → 1")
        void testLeetCodeExample2() {
            TreeNode root = node(1, leaf(2), null);
            assertEquals(1, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Single node, no children: [1] → 0")
        void testSingleNode() {
            assertEquals(0, instance.diameterOfBinaryTreeIteratively(leaf(1)));
        }

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.diameterOfBinaryTreeIteratively(null));
        }

        @Test
        @DisplayName("All-same values, perfect tree of depth 2 (7 nodes, all val=7) → 4")
        void testAllSameValuesPerfectTree() {
            TreeNode root = node(7,
                    node(7, leaf(7), leaf(7)),
                    node(7, leaf(7), leaf(7)));
            assertEquals(4, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Right-skewed chain, 5 nodes: 1→2→3→4→5 via right children → 4")
        void testRightSkewedChain() {
            TreeNode root = node(1, null,
                    node(2, null,
                            node(3, null,
                                    node(4, null, leaf(5)))));
            assertEquals(4, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Left-skewed chain, 5 nodes: 1→2→3→4→5 via left children → 4")
        void testLeftSkewedChain() {
            TreeNode root = node(1,
                    node(2,
                            node(3,
                                    node(4, leaf(5), null),
                                    null),
                            null),
                    null);
            assertEquals(4, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Perfect binary tree, depth 3 (15 nodes) with branching at every level → 6")
        void testPerfectTreeDeepBranching() {
            TreeNode l4a = node(4, leaf(8), leaf(9));
            TreeNode l4b = node(5, leaf(10), leaf(11));
            TreeNode l4c = node(6, leaf(12), leaf(13));
            TreeNode l4d = node(7, leaf(14), leaf(15));
            TreeNode l1 = node(2, l4a, l4b);
            TreeNode l2 = node(3, l4c, l4d);
            TreeNode root = node(1, l1, l2);
            assertEquals(6, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Node values at min/max constraint boundaries: [-100,100] → 1")
        void testBoundaryNodeValues() {
            TreeNode root = node(-100, leaf(100), null);
            assertEquals(1, instance.diameterOfBinaryTreeIteratively(root));
        }

        @Test
        @DisplayName("Moderately deep skewed chain (1000 nodes) resolves to full chain length")
        void testModeratelyDeepSkewedChain() {
            int depth = 1000;
            TreeNode current = leaf(depth);
            for (int v = depth - 1; v >= 1; v--) {
                current = node(v, current, null);
            }
            assertEquals(depth - 1, instance.diameterOfBinaryTreeIteratively(current));
        }
    }
}
