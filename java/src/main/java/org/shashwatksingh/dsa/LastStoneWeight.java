package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

 

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
Example 2:

Input: stones = [1]
Output: 1
 

Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000
*/

public class LastStoneWeight {
    public static void main(String[] args) {
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println(lastStoneWeight.solution(new int[] {2,7,4,1,8,1}));
        System.out.println(lastStoneWeight.solution(new int[] {1}));
    }

    // Using max heap
    public int solution(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : stones) {
            queue.add(num);
        }

        while (queue.size()>1) {
            int stone1 = queue.poll();
            int stone2 = queue.poll();
            if(stone1!=stone2) queue.add(stone1-stone2);
        }
        return !queue.isEmpty() ? queue.poll() : 0;
    }


    //Sort and preserve order
    public int solutionSortII(int[] stones) {
        List<Integer> list = new ArrayList<>();
        for (Integer num : stones) {
            list.add(num);
        }

        Collections.sort(list);

        while(list.size()>1) {
            int stone1 = list.remove(list.size()-1);
            int stone2 = list.remove(list.size()-1);
            if(stone1 != stone2) {
                int elem = stone1 - stone2;
                int index = Collections.binarySearch(list, elem);
                if(index<0) index = -index-1;
                list.add(index, elem);
            }
        }
        return list.isEmpty() ? 0 : list.get(list.size()-1);
    }

    //Sort and destroy order - not good
    public int solutionSortI(int[] stones) {
        int size = stones.length;
        while (size > 1) {
            Arrays.sort(stones, 0, size);
            int stone1 = stones[size-1];
            int stone2 = stones[size-2];
            size--;
            stones[size-1] = (stone1 != stone2) ? stone1 - stone2 : 0;
        }
        return stones[0];
    }



    // Brute force array simulation
    // Repeatedly find the largest stone. Break or add diff of that back to the array
    public int solutionBrute(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        for (int weight : stones) {
            stoneList.add(weight);
        }

        while(stoneList.size()>1) {
            int stone1 = removeLargest(stoneList);
            int stone2 = removeLargest(stoneList);
            if(stone1 != stone2) {
                stoneList.add(stone1-stone2);
            }
        }
        return !stoneList.isEmpty() ? stoneList.remove(0) : 0;
    }

    private int removeLargest(List<Integer> stones) {
        int indexOfLargest = stones.indexOf(Collections.max(stones));
        int result = stones.get(indexOfLargest);
        stones.set(indexOfLargest, stones.get(stones.size()-1));
        stones.remove(stones.size()-1);
        return result;
    }
}
