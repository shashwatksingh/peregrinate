package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.List;

public class HeapImplementation {

    List<Integer> list;
    
    public HeapImplementation() {
        list = new ArrayList<>();
    }

    private void print() {
        System.out.println(list.toString());
        System.out.println();
    }

    private void siftup(int index){
        int parent = getParent(index);
        while (list.get(parent)>list.get(index)) {
            swap(index, parent);
            index = parent;
            parent = getParent(index);
        }
    }

    private void siftDown(int index){
        int leftChild = getLeftChild(index), rightChild = getRightChild(index);
        int smallest = index;
        if(leftChild < list.size() && list.get(leftChild)< list.get(smallest)) smallest = leftChild;
        if(rightChild < list.size() && list.get(rightChild)< list.get(smallest)) smallest = rightChild;
        if(smallest!=index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private int getLeftChild(int index) {
        return 2*index+1;
    }

    private int getRightChild(int index) {
        return 2*index+2;
    }

    private boolean isEmpty() {
        return list.isEmpty();
    }

    private int getParent(int index) {
        return (index-1)/2;
    }

    public void add(int element) {
        list.add(element);
        siftup(list.size()-1);
    }

    public int poll() {
        if(isEmpty()) throw new IllegalStateException("The heap doesn't have values");
        int minElement = list.getFirst(), lastElement = list.removeLast();
        print();
        if(!isEmpty()) {
            list.set(0, lastElement);
            siftDown(0);
        }
        return minElement;
    }

    public int peek() {
        if(isEmpty()) throw new IllegalStateException("The heap doesn't have values");
        return list.get(0);
    }

    private void swap(int source, int target) {
        int temp = list.get(source);
        list.set(source, list.get(target));
        list.set(target, temp);
    }
}
