package org.shashwatksingh.dsa;

import org.shashwatksingh.dsa.helpers.ListNode;

/*
21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
*/

public class MergeTwoSortedLists {
    public ListNode mergeTwoLLIterative(ListNode list1, ListNode list2) {
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode sentinel = new ListNode();
        ListNode temp = sentinel;

        while (temp1!=null && temp2 != null) {
            if(temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        temp.next = temp1 == null ? temp2 : temp1;

        return sentinel.next;
    }

    public ListNode mergeTwoLLRecursive(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        else if (list2 == null) return list1;
        else if(list1.val <= list2.val) {
            list1.next = mergeTwoLLRecursive(list1.next, list2);
            return list1;
        } 
        list2.next = mergeTwoLLRecursive(list1, list2.next);
        return list2;
    }
}
