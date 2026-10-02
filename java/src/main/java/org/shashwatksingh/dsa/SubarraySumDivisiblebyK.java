package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.Map;

/*

974. Subarray Sums Divisible by K
Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

 

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
*/

public class SubarraySumDivisiblebyK {

    public static void main(String[] args) {
        SubarraySumDivisiblebyK subarraySumDivisiblebyK = new SubarraySumDivisiblebyK();
        System.out.println(subarraySumDivisiblebyK.subarraysDivByKArraySolution(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
    }

    public int subarraysDivByKPrefixArray(int[] nums, int k) {
        int n = nums.length;
        int[] PS = new int[n + 1];
        int sum = 0, ctr = 0;

        for (int i = 1; i < n + 1; i++) {
            sum += nums[i - 1];
            PS[i] = sum;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if ((PS[j] - PS[i]) % k == 0)
                    ctr++;
            }
        }

        return ctr;
    }

    public int subarraysDivByKMapSolution(int[] nums, int k) {
        int n = nums.length;
        int rem = 0, ctr = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //map{rem:count}
        map.put(0, 1);

        for (int i = 0; i < n; i++) {
            // rem = (rem + nums[i]) % k;
            // if (rem < 0) rem += k;
            rem = (rem + nums[i] % k + k) % k;
            if (map.containsKey(rem)) ctr+=map.get(rem);
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return ctr;
    }

    public int subarraysDivByKArraySolution(int[] nums, int k) {
        int n = nums.length;
        int rem = 0, ctr = 0;
        int[] modGroup = new int[k];
        modGroup[0] = 1;

        for (int i = 0; i < n; i++) {
            // rem = (rem + nums[i]) % k;
            // if (rem < 0) rem += k;
            rem = (rem + nums[i] % k + k) % k;
            ctr+=modGroup[rem];
           modGroup[rem]++;
        }

        return ctr;
    }

}
