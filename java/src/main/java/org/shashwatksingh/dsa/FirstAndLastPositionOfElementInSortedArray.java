package org.shashwatksingh.dsa;

/*
34. Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.
 

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
*/

public class FirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        FirstAndLastPositionOfElementInSortedArray falpoeisa = new FirstAndLastPositionOfElementInSortedArray();
        System.out.println(falpoeisa.findFirstPosition(new int[] { 5, 7, 7, 8, 8, 8, 8, 10 }, 8));
        System.out.println(falpoeisa.findLastPosition(new int[] { 5, 7, 7, 8, 8, 8, 8, 10 }, 8));
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = findFirstPosition(nums, target);
        if (firstOccurrence == -1)
            return new int[] { -1, -1 };
        return new int[] { firstOccurrence, findLastPosition(nums, target) };
    }

    private int findFirstPosition(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                if (mid == low || nums[mid - 1] != target) {
                    return mid;
                }
            }

            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    private int findLastPosition(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                if (mid == high || nums[mid + 1] != target) {
                    return mid;
                }
            }

            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }
}
