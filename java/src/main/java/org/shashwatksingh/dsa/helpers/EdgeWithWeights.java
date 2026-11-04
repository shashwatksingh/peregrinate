package org.shashwatksingh.dsa.helpers;

public class EdgeWithWeights implements Comparable<EdgeWithWeights> {
    int src;
    int dest;
    int weight;

    public EdgeWithWeights(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    @Override 
    public int compareTo(EdgeWithWeights other) {
        return this.weight - other.weight;
    }
}
