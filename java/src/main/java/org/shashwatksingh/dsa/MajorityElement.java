package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
169. Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
 

Constraints:
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
The input is generated such that a majority element will exist in the array.

Follow-up: Could you solve the problem in linear time and in O(1) space?
*/

public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        System.out.println(majorityElement.sortSolution(new int[]{2,2,1,1,1,2,2}));
    }

    public int bruteForceSolution(int[] nums) {
        int majFreq = nums.length/2;
        for (int i = 0; i < nums.length; i++) {
            int ctr = 1;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i] == nums[j]) ctr++;
            }
            if(ctr>majFreq) return nums[i];
        }
        return -1;
    }

    public int hashMapSolution(int[] nums) {
        int majFreq = nums.length/2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
            if(map.get(i)>majFreq) return i;
        }
        return -1;
    }

    public int sortSolution(int[] nums) {
        int majFreq = nums.length/2;
        Arrays.sort(nums);
        int curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]==nums[i-1]) {
                curr++;
            } else {
                if(curr>majFreq) return nums[i-1];
                curr = 1;
            }
        }
        if(curr>majFreq) return nums[nums.length-1];
        return -1;
    }

    public int sortSolutionOptimised(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
