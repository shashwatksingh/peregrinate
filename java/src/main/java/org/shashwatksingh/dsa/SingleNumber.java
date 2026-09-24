package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
136. Single Number
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.
 

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1
 

Constraints:
1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
*/

public class SingleNumber {

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        System.out.println(sn.bruteForce(new int[] { 2, 2, 1 }));
    }

    public int bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean seen = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j] && i != j)
                    seen = true;
            }
            if (!seen)
                return nums[i];
        }
        return -1;
    }

    public int twoPointerSolution(int[] nums) {
        Arrays.sort(nums);
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return -1;
        if (nums[0] != nums[1])
            return nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1])
                return nums[i];
        }
        return nums[nums.length - 1];
    }

    public int mathSolution(int[] nums) {
        int sumTotal = 0, sumSet = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) {
            sumTotal+=num;
            if(!set.contains(num)) {
                sumSet+=num;
                set.add(num);
            }
        }

        return 2*sumSet - sumTotal;
    }

    public int optimisedBinarySearchMethod(int[] nums) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid is odd, step back to make it even for comparison
            if (mid % 2 == 1) {
                mid--;
            }

            // If the pair is intact, the single element is further right
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                // If the pair is broken, the single element is to the left (or is mid)
                high = mid;
            }
        }

        return nums[low];
    }

    public int mapSolution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        return -1;
    }

    public int xorOperatorSolution(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
