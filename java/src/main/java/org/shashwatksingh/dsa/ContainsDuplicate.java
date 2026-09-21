package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashSet;

/*
217. Contains Duplicate
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true
Explanation:
The element 1 occurs at the indices 0 and 3.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

 

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

*/

public class ContainsDuplicate {

    public static void main(String[] args) {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();

    }

    public boolean setSolution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if(!set.add(i)) return true;
        }
        return false;
    }

    public boolean twoPointer(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) return true;
        }
        return false;
    }

    public boolean bruteForceSolution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]==nums[j]) return true;
            }
        }

        return false;
    }
}
