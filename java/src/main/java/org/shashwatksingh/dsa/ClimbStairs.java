package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints:
1 <= n <= 45
*/

public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.solutionMemoization(5));
        System.out.println(climbStairs.solutionBottomUp(5));
        System.out.println(climbStairs.solutionBottomUpOptimised(6));
    }

    //f(i,n) = f(i-1,n)+f(i-2,n)
    public int bruteForceRecursion(int n) {
        return climb_stairs(0, n);  
    }

    public int climb_stairs(int i, int n) {
        if(i>n) return 0;
        if(i==n) return 1;
        return climb_stairs(i+1, n) + climb_stairs(i+2, n);
    }

    public int solutionMemoization(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        return climb_stairs_memoization(0, n, memo);
    }

    public int climb_stairs_memoization(int i , int n, int[] memo) {
        if (i>n)  return 0;
        if (i==n) return 1;
        if(memo[i]!=-1) return memo[i];
        memo[i] = climb_stairs_memoization(i+1, n, memo) + climb_stairs_memoization(i+2, n, memo);
        return memo[i];
    }

    public int solutionBottomUp(int n) {
        if(n==1) return 1;
        int[] dp =new int[n+1];
        dp[0] = 0;
        dp[1] = 1; 
        dp[2] = 2;

        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int solutionBottomUpOptimised(int n) {
        if(n== 1) return 1;
        int first = 1;
        int second = 2;

        for (int i = 3; i < n+1; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        return second;
    }
}
