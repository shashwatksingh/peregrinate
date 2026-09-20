package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

public class SubarraySumEqualsK {
    public int solution(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if(map.containsKey(sum-k)) count+=map.get(sum-k);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }

        return count;
    }

    // Using prefix sum array
    public int solution2(int[] nums, int k) {
        int count = 0;
        int[] ps = new int[nums.length+1];
        ps[0] = nums[0];

        for (int i = 1; i <= nums.length; i++) {
            ps[i] = ps[i-1] + nums[i-1];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < ps.length; j++) {
                if (ps[j]-ps[i] == k) count++;
            }
        }

        return count;
    }

    // Brute Force - consider all possibilities of the array
    public int solution1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                int curr = 0;
                for (int i = start; i <= end; i++) {
                    curr += nums[i];
                }
                if (curr == k) count++;
            }
        }
        return count;
    }
}
