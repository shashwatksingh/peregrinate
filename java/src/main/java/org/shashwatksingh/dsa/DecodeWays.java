package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.Map;

/*
91. Decode Ways
You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. 
If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:
Input: s = "12"
Output: 2
Explanation:
"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation:
"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation:
"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

 

Constraints:
1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
*/

public class DecodeWays {

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.bruteForce("11106"));
        System.out.println(decodeWays.topDownWithMemoization("11106"));
        System.out.println(decodeWays.bottomUpTabulation("11106"));
        System.out.println(decodeWays.bottomUpSpaceOptimization("11106"));
        
    }

    public int bruteForce(String s) {
        return decodeWaysRecursive(0, s);
    }

    private int decodeWaysRecursive(int index, String s) {
        if(index == s.length()) return 1;
        if(s.charAt(index) == '0') return 0;
        int ways = decodeWaysRecursive(index+1, s);
        if(index+1<s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index+2));
            if(twoDigit >=10 && twoDigit <=26) {
                ways+=decodeWaysRecursive(index+2, s);
            }
        }
        return ways;
    }

    public int topDownWithMemoization(String s) {
        Map<Integer,Integer> map = new HashMap<>();
        return decodeWaysTopDownMemo(0, s, map);
    }

    private int decodeWaysTopDownMemo(int index, String s, Map<Integer, Integer> map) {
        if(index == s.length()) return 1;
        if(s.charAt(index) == '0') return 0;
        if(map.containsKey(index)) return map.get(index);
        int ways = decodeWaysTopDownMemo(index+1, s, map);
        if(index+1<s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index+2));
            if(twoDigit >=10 && twoDigit <=26) {
                ways+=decodeWaysTopDownMemo(index+2, s, map);
            }
        }
        map.put(index, ways);
        return ways;
    }

    public int bottomUpTabulation(String s) {
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }

            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }

        }
        return dp[len];
    }

    public int bottomUpSpaceOptimization(String s) {
        int len = s.length();
        int prev2 = 1;
        int prev1 = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int third = 0;
            if (s.charAt(i - 1) != '0') {
                third += prev1;
            }

            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                third += prev2;
            }
            prev2 = prev1;
            prev1 = third;
        }
        return prev1;
    }

}
