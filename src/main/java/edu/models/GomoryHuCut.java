package edu.models;

public class GomoryHuCut {
    private int[] vertexes;
    int weight;

    public GomoryHuCut(int[] vertexes, int weight) {
        this.vertexes = vertexes;
        this.weight = weight;
    }

    public int[] getVertexes() {
        return vertexes;
    }
    public void setVertexes(int[] vertexes) {
        this.vertexes = vertexes;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
