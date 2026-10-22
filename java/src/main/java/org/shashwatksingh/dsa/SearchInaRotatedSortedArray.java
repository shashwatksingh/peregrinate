package org.shashwatksingh.dsa;

/*
33. Search in Rotated Sorted Array

There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

 

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1
 

Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104

*/

public class SearchInaRotatedSortedArray {

    public static void main(String[] args) {
        SearchInaRotatedSortedArray  searchInaRotatedSortedArray = new SearchInaRotatedSortedArray();
        System.out.println(searchInaRotatedSortedArray.searchBinarySolution(new int[]{7,0,1,2,3,4,5,6}, 7));
    }

    public int searchAfterRemovingRotation(int[] nums, int target) {
        int len = nums.length;
        int[] temp = new int[(len*2)];

        for (int i = 0; i < len; i++) {
            temp[i] = nums[i];
            temp[i+len] = nums[i];
        }

        int k = 1;
        while (temp[k-1]<temp[k] && k<len) {
            k++;
        }

        int res = binarySearch(temp, k, k+len-1, target);

        return res == -1 ? -1 : res%len;
    }

    public int searchInRotatedParts(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;

        //Find the pivot
        while (left<right) {
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[right]) left = mid+1;
            else right = mid;
        }

        if(nums[left] == target) return left;

        int res = binarySearch(nums, 0, left-1, target);
        if(res != -1) return res;
        return binarySearch(nums, left, len-1, target);
    }

    public int searchBinarySolution(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;

        while (left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[left]<=nums[mid]) {
                if (nums[left]<=target && nums[mid] >= target) {
                    right = mid-1;
                } else{
                    left = mid+1;
                }
            } else {
                if (nums[right]>=target && nums[mid] <= target) {
                    left = mid+1;
                } else{
                    right = mid-1;
                }
            }
        }

        return -1;
    }

    private int binarySearch(int[]arr, int low, int high, int target) {
        
        while (low<=high) {
            int mid = low + (high-low)/2;
            if(target == arr[mid]) return mid;
            else if(target<arr[mid]) high = mid-1;
            else {low = mid+1;}
        }

        return -1;
    }

}
