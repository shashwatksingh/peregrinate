package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even numbers that can be formed using these digits.

Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.

 

Example 1:

Input: digits = [1,2,3,4]

Output: 12

Explanation: The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.

Example 2:

Input: digits = [0,2,2]

Output: 2

Explanation: The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.

Example 3:

Input: digits = [6,6,6]

Output: 1

Explanation: Only 666 can be formed.

Example 4:

Input: digits = [1,3,5]

Output: 0

Explanation: No even 3-digit numbers can be formed.

 

Constraints:

3 <= digits.length <= 10
0 <= digits[i] <= 9
*/

public class Unique3DigitNumber {
    private int res = 0;

    public static void main(String[] args) {
        Unique3DigitNumber unique3DigitNumber = new Unique3DigitNumber();
        // System.out.println(unique3DigitNumber.solutionRecursive(new int[]{1, 2, 3, 4}));
        System.out.println(unique3DigitNumber.solutionIterative(new int[]{1, 2, 3, 4}));
        
    }
 
    public int solutionRecursive(int[] digits) {
        Arrays.sort(digits);
        backtrack(digits, 0, 0, new boolean[digits.length]);
        return res;
    }

    private void backtrack(int[] digits, int currIndex, int num, boolean[] visited) {
        if(currIndex == 3) {
            if(num%2==0) res++;
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if(visited[i]) continue;
            // for identical digit, use the first copy first. Otherwise, you will create a duplicate branch. 
            if(i>0 && !visited[i-1] && digits[i] == digits[i-1]) continue;
            if(digits[i]==0 && currIndex==0) continue;
            visited[i]=true;
            backtrack(digits, currIndex+1, num*10+digits[i], visited);
            visited[i] = false;
        }
    }

    public int solutionIterative(int[] digits) {
        int count = 0;
        boolean[] seen = new boolean[1000];
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            if(digits[i]==0) continue;
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                for (int k = 0; k < n; k++) {
                    if(i==k || j==k || digits[k]%2!=0) continue;
                    int x = digits[i]*100 + digits[j]*10 + digits[k];
                    if(!seen[x]) {
                        seen[x] = true;
                        count++;
                    } 
                }
            }
        }
        return count;
    }
}
