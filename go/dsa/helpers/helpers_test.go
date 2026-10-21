package helpers

import (
	"slices"
	"testing"
)

func TestListRoundTrip(t *testing.T) {
	tests := []struct {
		name string
		vals []int
	}{
		{"Empty list: [] → []", []int{}},
		{"Single node: [5] → [5]", []int{5}},
		{"Several nodes: [1,2,3] → [1,2,3]", []int{1, 2, 3}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := ToSlice(NewList(tt.vals...)); !slices.Equal(got, tt.vals) {
				t.Errorf("ToSlice(NewList(%v)) = %v", tt.vals, got)
			}
		})
	}
}

func TestNewTree(t *testing.T) {
	t.Run("Empty input → nil", func(t *testing.T) {
		if NewTree() != nil {
			t.Error("expected nil tree")
		}
	})
	t.Run("Level order with gaps: (1,Null,2,3) → 1, right 2, 2's left 3", func(t *testing.T) {
		root := NewTree(1, Null, 2, 3)
		if root.Val != 1 || root.Left != nil || root.Right == nil || root.Right.Val != 2 {
			t.Fatalf("unexpected shape at root: %+v", root)
		}
		if root.Right.Left == nil || root.Right.Left.Val != 3 || root.Right.Right != nil {
			t.Errorf("unexpected shape under node 2: %+v", root.Right)
		}
	})
}
