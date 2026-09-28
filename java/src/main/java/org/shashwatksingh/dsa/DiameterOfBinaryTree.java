package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import org.shashwatksingh.dsa.helpers.TreeNode;

/*
543. Diameter of Binary Tree
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1
 

Constraints:
The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
*/

public class DiameterOfBinaryTree {

    int maxDiameter;

    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        dfs(root);
        return maxDiameter;
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = dfs(root.left);
        int rightHeight = dfs(root.right);
        maxDiameter = Math.max(maxDiameter, rightHeight+leftHeight);
        return 1+Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTreeIteratively(TreeNode root) {
        if(root == null) return 0;
        int diameter = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);
        HashMap<TreeNode, Integer> map = new HashMap<>();
        map.put(null, 0);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if(curr.left!=null && !map.containsKey(curr.left)) {
                stack.push(curr.left);
            } else if(curr.right!=null && !map.containsKey(curr.right)) {
                stack.push(curr.right);
            } else {
                stack.pop();

                int leftHeight = map.get(curr.left);
                int rightHeight = map.get(curr.right);

                diameter = Math.max(diameter, leftHeight+rightHeight);
                map.put(curr, 1+Math.max(leftHeight, rightHeight));
            }
        }
        return diameter;
    }

}
