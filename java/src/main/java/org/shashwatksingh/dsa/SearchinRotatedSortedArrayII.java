package org.shashwatksingh.dsa;

/*

81. Search in Rotated Sorted Array II
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.
 
Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 

Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104

Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
*/

public class SearchinRotatedSortedArrayII {

    public static void main(String[] args) {
        SearchinRotatedSortedArrayII sirsa2 = new SearchinRotatedSortedArrayII();
        System.out.println(sirsa2.searchInRotatedParts(new int[] {2,5,6,0,0,1,2}, 0));
        System.out.println(sirsa2.searchInRotatedParts(new int[] {2,5,6,0,0,1,2}, 3));
        System.out.println(sirsa2.searchInRotatedParts(new int[] {1, 0, 1, 1, 1}, 0));
        System.out.println(sirsa2.searchInRotatedParts(new int[]{2, 2, 2, 2, 2, 2, 2}, 2));
    }

    public boolean searchAfterRemovingRotation(int[] nums, int target) {
        int len = nums.length;
        if(len == 1) return nums[0]==target;
        int[] temp = new int[(len*2)];

        for (int i = 0; i < len; i++) {
            temp[i] = nums[i];
            temp[i+len] = nums[i];
        }

        int k = 1;
        while (temp[k-1]<=temp[k] && k<len) {
            k++;
        }

        return binarySearch(temp, k, k+len-1, target);
    }

    public boolean searchInRotatedParts(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;

        //Find the pivot
        while (left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[right]) left = mid+1;
            else if(nums[mid]<nums[right]) right = mid-1;
            else right--;
        }

        if(nums[left] == target) return true;

        return  binarySearch(nums, 0, left, target) || binarySearch(nums, left+1, len-1, target);
    }

    private boolean binarySearch(int[]arr, int low, int high, int target) {
        
        while (low<=high) {
            int mid = low + (high-low)/2;
            if(target == arr[mid]) return true;
            else if(target<arr[mid]) high = mid-1;
            else low = mid+1;
        }

        return false;
    }

    public boolean search(int[] nums, int target) {
        /*
        step1:
        start = 0, end = 5, mid = (0+5)/2 = 2
        [0 1 2 3 4 5]
        
        1) nums[mid] == target, condition fails
        2) nums[mid] > nums[start], condition fails
        3) nums[mid] < nums[start], condition fails
        4) instead of increasing start, if you are decreasing end,
        then end will become , end = 4
        
        
        step2:
        start = 0, end = 4, mid = (0+4)/2 = 2
        again all condition fails, you will decrease end, then end = 3
        
        Now you missed the target. 
        why ? it leads to missing. Because, we are checking the condition with start not end i.e nums[mid] < nums[start] or nums[mid] > nums[start],
        so you have to increase the start that is the only one option you have.
        
        */
        int len = nums.length;
        int left = 0, right = len-1;

        while (left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return true;
            else if(nums[left] < nums[mid]) {
                if (nums[left]<=target && nums[mid] >= target) {
                    right = mid-1;
                } else{
                    left = mid+1;
                }
            } else if(nums[mid] < nums[left]) {
                if (nums[right]>=target && nums[mid] <= target) {
                    left = mid+1;
                } else{
                    right = mid-1;
                }
            } else{
                left++;
            }
        }

        return false;
    }

}
