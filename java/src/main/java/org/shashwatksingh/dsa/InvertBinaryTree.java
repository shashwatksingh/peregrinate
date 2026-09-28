package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Deque;

import org.shashwatksingh.dsa.helpers.TreeNode;

/*
226. Invert Binary Tree
Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

public class InvertBinaryTree {
    
    public TreeNode invertTreeRecursive(TreeNode root) {
        TreeNode temp = invertTreeRecursive(root.left);
        root.left = invertTreeRecursive(root.right);
        root.right = temp;
        return root;
    }

    public TreeNode invertTreeIterative(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if(curr.left!=null) queue.add(curr.left);
            if(curr.right!=null) queue.add(curr.right);
        }
        return root;
    }

}
