package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.bruteForceSolution(new int[]{1,2,5}, 11));
        System.out.println(coinChange.topDownWithMemoizationSolution(new int[]{1,2,5}, 11));
        
    }

    public int bruteForceSolution(int[] coins, int amount) {
        int res = dfs(coins, amount);
        return res == Integer.MAX_VALUE ? -1 : res; 
    }

    private int dfs(int[] coins, int target) {
        if (target == 0) return 0;
        if(target < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = dfs(coins, target - coins[i]);
            
            // Only update min if the downstream call returned a valid path
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }
        return min;
    }

    public int topDownWithMemoizationSolution(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = dfsWithMemoization(coins, amount, map);
        return res == Integer.MAX_VALUE ? -1 : res; 
    }

    private int dfsWithMemoization(int[] coins, int target, Map<Integer, Integer> map) {
        if (target == 0) return 0;
        if (target < 0) return Integer.MAX_VALUE;
        if(map.containsKey(target)) return map.get(target);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = dfsWithMemoization(coins, target - coins[i], map);
            
            // Only update min if the downstream call returned a valid path
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }
        map.put(target, min); 
        return min;
    }

    public int bottomUpSolution(int[] coins, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, target + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= target; i++) {
            for (int coin : coins) {
                if(i-coin>=0) dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return dp[target] > target ? -1 : dp[target];
    }
}
