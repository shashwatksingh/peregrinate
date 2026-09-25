package org.shashwatksingh.dsa;

import java.util.LinkedList;
import java.util.Queue;

import org.shashwatksingh.dsa.helpers.TreeNode;

/*
104. Maximum Depth of Binary Tree
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2
 

Constraints:
The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
*/

public class MaxDepthOfBinaryTree {

    public int maxDepthRecursive(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) {
            return 1+dfs(root.right);
        } else if(root.right == null){
            return 1+dfs(root.left);
        } else {
            return 1+Math.max(dfs(root.left), dfs(root.right));
        }
    }

    public int maxDepthBfs(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(currNode.left!=null) queue.add(currNode.left);
                if(currNode.right!=null) queue.add(currNode.right);
            }
        }
        return count;
    }

}
