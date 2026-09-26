package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
523. Continuous Subarray Sum
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:
i. its length is at least two, and
ii. the sum of the elements of the subarray is a multiple of k.

Note that:
A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false
 

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
*/

public class ContinousSubArraySum {

    public static void main(String[] args) {
        ContinousSubArraySum csas = new ContinousSubArraySum();
        System.out.println(csas.checkSubarraySumPrefixMethod(new int[]{23,2,4,6,7}, 6));
        System.out.println(csas.checkSubarraySum(new int[]{23,2,4,6,7}, 6)); 
    }

    public boolean checkSubarraySumPrefixMethod(int[] nums, int k) {
        //prepare prefix sum array
        int n = nums.length;
        int[] PS = new int[n+1];
        PS[0] = 0;

        for (int i = 0; i < n; i++) {
            PS[i+1] = PS[i] + nums[i];
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+2; j <= n; j++) {
                if((PS[j]-PS[i])%k==0) return true;
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> modSeen = new HashMap<>();
        modSeen.put(0, -1);
        int prefixMod = 0;

        for (int i = 0; i < n; i++) {
            prefixMod= (prefixMod + nums[i])%k;

            if (modSeen.containsKey(prefixMod)) {
                // ensures that the size of subarray is at least 2
                if (i - modSeen.get(prefixMod) > 1) {
                    return true;
                }
            } else {
                // mark the value of prefixMod with the current index.
                modSeen.put(prefixMod, i);
            }
        }

        return false;
    }
}
