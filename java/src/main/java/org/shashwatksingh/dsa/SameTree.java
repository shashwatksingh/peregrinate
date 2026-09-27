package org.shashwatksingh.dsa;

import java.util.LinkedList;
import java.util.Queue;

import org.shashwatksingh.dsa.helpers.TreeNode;

/*
100. Same Tree
Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false
 

Constraints:
The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104

*/

public class SameTree {

    public static void main(String[] args) {
        SameTree solution = new SameTree();

        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));

        TreeNode p3 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q3 = new TreeNode(1, new TreeNode(1), new TreeNode(2));

        System.out.println("=== Example 1: p=[1,2,3], q=[1,2,3] (expected true) ===");
        System.out.println("Recursive: " + solution.isSameTreeRecursive(p1, q1));
        System.out.println("Iterative: " + solution.isSameTreeIterative(p1, q1));

        System.out.println("=== Example 2: p=[1,2], q=[1,null,2] (expected false) ===");
        System.out.println("Recursive: " + solution.isSameTreeRecursive(p2, q2));
        System.out.println("Iterative: " + solution.isSameTreeIterative(p2, q2));

        System.out.println("=== Example 3: p=[1,2,1], q=[1,1,2] (expected false) ===");
        System.out.println("Recursive: " + solution.isSameTreeRecursive(p3, q3));
        System.out.println("Iterative: " + solution.isSameTreeIterative(p3, q3));

        System.out.println("=== Both empty trees: p=null, q=null (expected true) ===");
        System.out.println("Recursive: " + solution.isSameTreeRecursive(null, null));
        System.out.println("Iterative: " + solution.isSameTreeIterative(null, null));
    }

    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if(p==null && q == null) return true;
        if(p==null || q == null) return false;
        return p.val == q.val && isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
    }

    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(!check(p,q)) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int sizeP = queue1.size();
            
            for (int i = 0; i < sizeP; i++) {
                TreeNode currP = queue1.poll();
                TreeNode currQ = queue2.poll();
                if (!check(currP, currQ)) return false;
                if (currP == null) continue;

                queue1.add(currP.left);
                queue2.add(currQ.left);
                queue1.add(currP.right);
                queue2.add(currQ.right);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    private boolean check(TreeNode p, TreeNode q) {
        if(p==null && q == null) return true;
        if(p==null || q == null) return false;
        if(p.val == q.val) return true;
        return false;
    }
    
}
