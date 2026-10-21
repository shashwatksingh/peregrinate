package helpers

// ListNode is a singly linked list node (LeetCode's definition).
type ListNode struct {
	Val  int
	Next *ListNode
}

// NewList builds a linked list from vals and returns its head (nil for no values).
func NewList(vals ...int) *ListNode {
	dummy := &ListNode{}
	curr := dummy
	for _, v := range vals {
		curr.Next = &ListNode{Val: v}
		curr = curr.Next
	}
	return dummy.Next
}

// ToSlice returns the values of the list starting at head, in order.
func ToSlice(head *ListNode) []int {
	vals := []int{}
	for ; head != nil; head = head.Next {
		vals = append(vals, head.Val)
	}
	return vals
}
