package edu.models;

public class Graph {
    private int[][] matrix;
    private boolean[][] usedForSearchMask;

    public Graph(int[][] matrix) {
        this.matrix = matrix;
        this.usedForSearchMask = new boolean[matrix.length][matrix.length];
    }

    public int[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean[][] getUsedForSearchMask() {
        return usedForSearchMask;
    }
    public void setUsedForSearchMask(boolean[][] usedForSearchMask) {
        this.usedForSearchMask = usedForSearchMask;
    }
}
