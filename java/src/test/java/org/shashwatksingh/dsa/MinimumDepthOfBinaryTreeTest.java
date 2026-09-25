package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MinimumDepthOfBinaryTree Tests")
class MinimumDepthOfBinaryTreeTest {

    private MinimumDepthOfBinaryTree instance;

    @BeforeEach
    void setUp() {
        instance = new MinimumDepthOfBinaryTree();
    }

    private static TreeNode leaf(int val) {
        return new TreeNode(val);
    }

    private static TreeNode node(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionRecursion()  — DFS that skips null children instead of treating them as leaves, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionRecursion() — recursive DFS ignoring one-sided null children")
    class SolutionRecursionTests {

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.solutionRecursion(null));
        }

        @Test
        @DisplayName("Single node, no children: [5] → 1")
        void testSingleNode() {
            assertEquals(1, instance.solutionRecursion(leaf(5)));
        }

        @Test
        @DisplayName("LeetCode Example 1: [3,9,20,null,null,15,7] → 2")
        void testLeetCodeExample1() {
            TreeNode root = node(3, leaf(9), node(20, leaf(15), leaf(7)));
            assertEquals(2, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,null,3,null,4,null,5,null,6] (right-skewed) → 5")
        void testLeetCodeExample2() {
            TreeNode root = node(2, null,
                    node(3, null,
                            node(4, null,
                                    node(5, null, leaf(6)))));
            assertEquals(5, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("Left-skewed mirror of Example 2: 2→3→4→5→6 via left children → 5")
        void testLeftSkewedTree() {
            TreeNode root = node(2,
                    node(3,
                            node(4,
                                    node(5, leaf(6), null),
                                    null),
                            null),
                    null);
            assertEquals(5, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("Balanced tree, both leaves at same depth: [0,1,2] → 2")
        void testBalancedTreeBothLeavesSameDepth() {
            TreeNode root = node(0, leaf(1), leaf(2));
            assertEquals(2, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("Shallow leaf beats a deep one-sided sibling subtree: root has a leaf on one side and a 4-deep chain on the other → 2")
        void testMinIsCorrectlyTheShallowerSide() {
            TreeNode deepChain = node(6, null,
                    node(7, null,
                            node(8, null, leaf(9))));
            TreeNode root = node(0, leaf(1), deepChain);
            assertEquals(2, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("Node values at min/max constraint boundaries: [-1000,1000,0] → 2")
        void testBoundaryNodeValues() {
            TreeNode root = node(-1000, leaf(1000), leaf(0));
            assertEquals(2, instance.solutionRecursion(root));
        }

        @Test
        @DisplayName("Moderately deep skewed chain (1000 nodes) resolves to full chain length")
        void testModeratelyDeepSkewedChain() {
            int depth = 1000;
            TreeNode current = leaf(depth);
            for (int v = depth - 1; v >= 1; v--) {
                current = node(v, current, null);
            }
            assertEquals(depth, instance.solutionRecursion(current));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionbfs()  — level-order BFS, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionbfs() — ⚠️ BUGGY — count increments per node dequeued instead of per level")
    class SolutionBfsTests {

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.solutionbfs(null));
        }

        @Test
        @DisplayName("single node, no children")
        void testSingleNode() {
            assertEquals(1, instance.solutionbfs(leaf(5)));
        }

        @Test
        @DisplayName("LeetCode Example 1: [3,9,20,null,null,15,7]")
        void testLeetCodeExample1() {
            TreeNode root = node(3, leaf(9), node(20, leaf(15), leaf(7)));
            assertEquals(2, instance.solutionbfs(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,null,3,null,4,null,5,null,6] (right-skewed)")
        void testLeetCodeExample2() {
            TreeNode root = node(2, null,
                    node(3, null,
                            node(4, null,
                                    node(5, null, leaf(6)))));
            assertEquals(5, instance.solutionbfs(root));
        }

        @Test
        @DisplayName("balanced tree, both leaves at same depth: [0,1,2]")
        void testBalancedTreeBothLeavesSameDepth() {
            TreeNode root = node(0, leaf(1), leaf(2));
            assertEquals(2, instance.solutionbfs(root));
        }

        @Test
        @DisplayName("non-leaf sibling dequeued first inflates the count arbitrarily")
        void testDistractorSiblingInflatesCount() {
            TreeNode nonLeafSibling = node(1, leaf(3), leaf(4));
            TreeNode shallowLeaf = leaf(2);
            TreeNode root = node(0, nonLeafSibling, shallowLeaf);
            assertEquals(2, instance.solutionbfs(root));
        }
    }
}
