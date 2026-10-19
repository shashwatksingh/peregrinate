package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Find Target Indices After Sorting Array
You are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. 
If there are no target indices, return an empty list. 
The returned list must be sorted in increasing order.

Example 1:
Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.

Example 2:
Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.

Example 3:
Input: nums = [1,2,5,2,3], target = 5
Output: [4]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 5 is 4.
 

Constraints:
1 <= nums.length <= 100
1 <= nums[i], target <= 100
*/

public class FindTargetIndicesAfterSortingArray {
    public List<Integer> targetIndicesSorting(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) res.add(i);
        }
        return res;
    }

    public List<Integer> targetIndices(int[] nums, int target) {
        int ctr1 = 0, ctr2 = 0;
        for(int num: nums) {
            if(num<target) ctr1++;
            else if(num==target) ctr2++;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = ctr1; i < ctr1+ctr2; i++) {
            res.add(i);
        }
        return res;
    }

}
