package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
279. Perfect Squares
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. 
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

Constraints:
1 <= n <= 104
*/

public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        // System.out.println(ps.numSquaresBottomUpWithTabulation(12));
        // System.out.println(ps.numSquaresBottomUpWithTabulation(13));

        // System.out.println(ps.numSquaresTopDownWithMemoization(12));
        // System.out.println(ps.numSquaresTopDownWithMemoization(13));

        System.out.println(ps.numSquaresRecursive(1));
    }

    public int numSquaresRecursive(int n) {
        int result = dfs(0, n);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(int i, int n) {
        int min = Integer.MAX_VALUE;
        if(i>n) return min;
        if(i==n) return 0;

        for (int j = 1; j*j <= n; j++) {
            int res = dfs(i+j*j, n);
            if(res!=Integer.MAX_VALUE) min = Math.min(1+res, min);
        }
        return min;
    }

    public int numSquaresTopDownWithMemoization(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        return dfsWithMemoization(0, n, memo);
    }

    private int dfsWithMemoization(int i, int n, int[] memo) {
        if(i==n) return 0;
        int min = Integer.MAX_VALUE;
        if(i>n) return min;
        if(memo[i]!=-1) return memo[i];
        for (int j = 1; j*j <= n; j++) {
            int res = dfsWithMemoization(i+j*j, n, memo);
            // System.out.println("i: " + i + " j: " + j + " res: " + res);
            if(res!=Integer.MAX_VALUE) min = Math.min(1+res, min);
            memo[i] = min;
        }
        return memo[i];
    }

    public int numSquaresBottomUpWithTabulation(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= n; j++) {
                if(i-j*j>=0) dp[i] = Math.min(dp[i], 1+dp[i-j*j]);
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }

   public int numSquaresBottomUpWithSpaceOptimisation(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= n; j++) {
                if(i-j*j>=0) dp[i] = Math.min(dp[i], 1+dp[i-j*j]);
            }
        }
        return dp[n] > n ? -1 : dp[n];
    }

}
