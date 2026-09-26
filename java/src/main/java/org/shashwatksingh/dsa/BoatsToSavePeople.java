package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
881. Boats to Save People

You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. 
Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

 

Example 1:
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
 

Constraints:
1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
*/

public class BoatsToSavePeople {

    public static void main(String[] args) {
        BoatsToSavePeople btsv = new BoatsToSavePeople();
        System.out.println(btsv.numRescueBoats(new int[]{1,2}, 3));
        System.out.println(btsv.numRescueBoats(new int[]{3,2,2,1}, 3));
        System.out.println(btsv.numRescueBoats(new int[]{3,2,2,1}, 3));
    }
    
    private int minBoats;
    public int numRescueBoatsBruteForce(int[] people, int limit) {
        int n = people.length;
        boolean[] visited = new boolean[n];
        this.minBoats = n; // Worst case: each person gets their own boat

        backtrack(people, limit, visited, 0, 0);
        return this.minBoats;
    }

    private void backtrack(int[] people, int limit, boolean[] visited, int index, int currentBoats) {
        int n = people.length;

        // Pruning: If our current boat count is already worse than the best found, stop exploring
        if (currentBoats >= minBoats) {
            return;
        }

        // Base Case: If all people have been checked
        if (index == n) {
            minBoats = Math.min(minBoats, currentBoats);
            return;
        }

        // If the current person is already placed in a boat by a previous pairing, move to the next person
        if (visited[index]) {
            backtrack(people, limit, visited, index + 1, currentBoats);
            return;
        }

        // Option 1: Put the current person alone in a boat
        visited[index] = true;
        backtrack(people, limit, visited, index + 1, currentBoats + 1);

        // Option 2: Try pairing the current person with every other available person
        for (int j = index + 1; j < n; j++) {
            if (!visited[j] && (people[index] + people[j] <= limit)) {
                visited[j] = true; // Pair them up
                backtrack(people, limit, visited, index + 1, currentBoats + 1);
                visited[j] = false; // Backtrack the pairing choice
            }
        }

        visited[index] = false; // Backtrack the individual choice
    }

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int left = 0, right = n-1, ctr = 0;

        while(left<=right) {
            if(people[left]+people[right]<=limit) {
                left++;
                right--;
            } else { // greedy approach to send person with maximum weight
                right--;
            }
            ctr++;
        }
        return ctr;
    }
}
