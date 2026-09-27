package org.shashwatksingh.dsa;

import java.util.Deque;
import java.util.LinkedList;

import org.shashwatksingh.dsa.helpers.TreeNode;

/*
101. Symmetric Tree
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
*/

public class SymmetricTree {

    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val == right.val)
            return isMirror(left.left, right.right) && isMirror(left.right, right.left);
        return false;
    }

    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode last = queue.poll();
            // If both are null, they are symmetric; move to the next pair
            if (first == null && last == null) continue;

            // If only one is null, or their values don't match, it's not symmetric
            if (first == null || last == null) return false;
            if (first.val != last.val) return false;
            
            queue.add(first.left);
            queue.add(last.right);
            queue.add(first.right);
            queue.add(last.left);
        }

        return true;
    }

}
