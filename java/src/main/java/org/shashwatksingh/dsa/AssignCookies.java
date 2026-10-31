package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
455. Assign Cookies

Assume you are an awesome parent and want to give your children some cookies.
But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. 
If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. 
Your goal is to maximize the number of your content children and output the maximum number.

 

Example 1:
Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.

Example 2:
Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
 

Constraints:
1 <= g.length <= 3 * 104
0 <= s.length <= 3 * 104
1 <= g[i], s[j] <= 231 - 1
 

Note: This question is the same as 2410: Maximum Matching of Players With Trainers.

*/

public class AssignCookies {

    public static void main(String[] args) {
        AssignCookies assignCookies = new AssignCookies();
        System.out.println(assignCookies.findContentChildrenBrute(new int[] { 1, 2, 3 }, new int[] { 1, 1 }));
        System.out.println(assignCookies.findContentChildrenBrute(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));

        System.out.println(assignCookies.findContentChildren(new int[] { 1, 2, 3 }, new int[] { 1, 1 }));
        System.out.println(assignCookies.findContentChildren(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));
    }

    public int findContentChildrenBrute(int[] g, int[] s) {
        int ctr = 0;
        boolean[] visited = new boolean[g.length];
        for (int size : s) {
            int best = -1;
            for (int i = g.length - 1; i >= 0; i--) {
                if (!visited[i] && g[i] <= size && (best == -1 || g[i] > g[best])) {
                    best = i;
                }
            }
            if (best != -1) {
                visited[best] = true;
                ctr++;
            }
        }
        return ctr;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ctr = 0;
        int ptr1 = g.length-1, ptr2 = s.length-1;

        while (ptr1 >= 0 && ptr2 >= 0) {
            while (ptr1>=0 && g[ptr1]>s[ptr2]) ptr1--;
            if(ptr1>=0 && g[ptr1]<=s[ptr2]) {
                ptr1--;
                ptr2--;
                ctr++;
            }
        }

        return ctr;
    }

}
