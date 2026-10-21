package helpers

import "math"

// TreeNode is a binary tree node (LeetCode's definition).
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Null marks a missing node in the level-order input of NewTree.
const Null = math.MinInt

// NewTree builds a binary tree from LeetCode-style level-order values, where Null
// marks a missing child. For example NewTree(1, Null, 2, 3) is 1 -> right 2 -> left 3.
func NewTree(vals ...int) *TreeNode {
	if len(vals) == 0 || vals[0] == Null {
		return nil
	}
	root := &TreeNode{Val: vals[0]}
	queue := []*TreeNode{root}
	i := 1
	for len(queue) > 0 && i < len(vals) {
		node := queue[0]
		queue = queue[1:]
		if i < len(vals) && vals[i] != Null {
			node.Left = &TreeNode{Val: vals[i]}
			queue = append(queue, node.Left)
		}
		i++
		if i < len(vals) && vals[i] != Null {
			node.Right = &TreeNode{Val: vals[i]}
			queue = append(queue, node.Right)
		}
		i++
	}
	return root
}
