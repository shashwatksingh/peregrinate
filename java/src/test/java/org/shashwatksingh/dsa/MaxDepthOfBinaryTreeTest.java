package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MaxDepthOfBinaryTree Tests")
class MaxDepthOfBinaryTreeTest {

    private MaxDepthOfBinaryTree instance;

    @BeforeEach
    void setUp() {
        instance = new MaxDepthOfBinaryTree();
    }

    private static TreeNode leaf(int val) {
        return new TreeNode(val);
    }

    private static TreeNode node(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    // ═══════════════════════════════════════════════════════════
    //  maxDepthRecursive()  — recursive DFS, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("maxDepthRecursive() — recursive DFS")
    class MaxDepthRecursiveTests {

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.maxDepthRecursive(null));
        }

        @Test
        @DisplayName("Single node, no children: [5] → 1")
        void testSingleNode() {
            assertEquals(1, instance.maxDepthRecursive(leaf(5)));
        }

        @Test
        @DisplayName("LeetCode Example 1: [3,9,20,null,null,15,7] → 3")
        void testLeetCodeExample1() {
            TreeNode root = node(3, leaf(9), node(20, leaf(15), leaf(7)));
            assertEquals(3, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,null,2] → 2")
        void testLeetCodeExample2() {
            TreeNode root = node(1, null, leaf(2));
            assertEquals(2, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Right-skewed chain, 5 nodes: 1→2→3→4→5 via right children → 5")
        void testRightSkewedTree() {
            TreeNode root = node(1, null,
                    node(2, null,
                            node(3, null,
                                    node(4, null, leaf(5)))));
            assertEquals(5, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Left-skewed chain, 5 nodes: 1→2→3→4→5 via left children → 5")
        void testLeftSkewedTree() {
            TreeNode root = node(1,
                    node(2,
                            node(3,
                                    node(4, leaf(5), null),
                                    null),
                            null),
                    null);
            assertEquals(5, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Balanced tree, both leaves at same depth: [0,1,2] → 2")
        void testBalancedTreeBothLeavesSameDepth() {
            TreeNode root = node(0, leaf(1), leaf(2));
            assertEquals(2, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Max is correctly the deeper one-sided sibling: root has a leaf on one side and a 4-deep chain on the other → 5")
        void testMaxIsCorrectlyTheDeeperSide() {
            TreeNode deepChain = node(6, null,
                    node(7, null,
                            node(8, null, leaf(9))));
            TreeNode root = node(0, leaf(1), deepChain);
            assertEquals(5, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Node values at min/max constraint boundaries: [-100,100,0] → 2")
        void testBoundaryNodeValues() {
            TreeNode root = node(-100, leaf(100), leaf(0));
            assertEquals(2, instance.maxDepthRecursive(root));
        }

        @Test
        @DisplayName("Moderately deep skewed chain (1000 nodes) resolves to full chain length")
        void testModeratelyDeepSkewedChain() {
            int depth = 1000;
            TreeNode current = leaf(depth);
            for (int v = depth - 1; v >= 1; v--) {
                current = node(v, current, null);
            }
            assertEquals(depth, instance.maxDepthRecursive(current));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  maxDepthBfs()  — level-order BFS, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("maxDepthBfs() - queue bfs")
    class MaxDepthBfsTests {

        @Test
        @DisplayName("Empty tree: root=null → 0")
        void testEmptyTree() {
            assertEquals(0, instance.maxDepthBfs(null));
        }

        @Test
        @DisplayName("single node, no children: [5] → 1 ")
        void testSingleNode() {
            assertEquals(1, instance.maxDepthBfs(leaf(5)));
        }

        @Test
        @DisplayName("LeetCode Example 1: [3,9,20,null,null,15,7] → 3")
        void testLeetCodeExample1() {
            TreeNode root = node(3, leaf(9), node(20, leaf(15), leaf(7)));
            assertEquals(3, instance.maxDepthBfs(root));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,null,2] → 2")
        void testLeetCodeExample2() {
            TreeNode root = node(1, null, leaf(2));
            assertEquals(2, instance.maxDepthBfs(root));
        }

        @Test
        @DisplayName("balanced tree, both leaves at same depth: [0,1,2] → 2")
        void testBalancedTreeBothLeavesSameDepth() {
            TreeNode root = node(0, leaf(1), leaf(2));
            assertEquals(2, instance.maxDepthBfs(root));
        }
    }
}
