package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
347. Top K Frequent Elements

Given an integer array nums and an integer k, return the k most frequent elements. 
You may return the answer in any order.


Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Example 3:
Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

public class TopKFrequentElement {

    public static void main(String[] args) {
        TopKFrequentElement topK = new TopKFrequentElement();
        Arrays.stream(topK.solutionI(new int[] {1,1,1,2,2,3}, 2)).forEach(System.out::println);
    }

    public int[] solutionI(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> freqMap.get(a)-freqMap.get(b));
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            heap.add(entry.getKey());
            if(heap.size()>k) {
                heap.poll();
            }
        }

        return heap.stream().mapToInt(Integer::intValue).toArray();
    }
    
}
