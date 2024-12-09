package edu.models;

public class GomoryHuCutNode {
    private int[] vertex;
    private int weight;
    private GomoryHuCutNode parent;
    private GomoryHuCutNode[] children;

    public GomoryHuCutNode() {
        this.vertex = new int[0];
        this.weight = 0;
        this.parent = null;
        this.children = new GomoryHuCutNode[0];
    }

    public GomoryHuCutNode(int[] vertex, int weight, GomoryHuCutNode parent, GomoryHuCutNode[] children) {
        this.vertex = vertex;
        this.weight = weight;
        this.parent = parent;
        this.children = children;
    }

    public int[] getVertex() {
        return vertex;
    }
    public void setVertex(int[] vertex) {
        this.vertex = vertex;
    }
    public void addVertex(int vertex) {
        int[] newVertex = new int[this.vertex.length + 1];
        for (int i = 0; i < this.vertex.length; i++) {
            newVertex[i] = this.vertex[i];
        }
        newVertex[this.vertex.length] = vertex;
        this.vertex = newVertex;
    }
    public void removeVertex(int vertex) {
        int[] newVertex = null;
        for (int i = 0; i < this.vertex.length; i++) {
            if(this.vertex[i] == vertex) {
                newVertex = new int[this.vertex.length - 1];
                for (int j = 0; j < i; j++) {
                    newVertex[j] = this.vertex[j];
                }
                for (int j = i + 1; j < this.vertex.length; j++) {
                    newVertex[j-1] = this.vertex[j];
                }
            }
        }
        this.vertex = newVertex;
    }


    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }


    public GomoryHuCutNode getParent() {
        return parent;
    }
    public void setParent(GomoryHuCutNode parent) {
        this.parent = parent;
    }

    public GomoryHuCutNode[] getChildren() {
        return children;
    }
    public void setChildren(GomoryHuCutNode[] children) {
        this.children = children;
    }
    public void addChild(GomoryHuCutNode child) {
        GomoryHuCutNode[] newChildren = new GomoryHuCutNode[this.children.length + 1];
        for (int i = 0; i < this.children.length; i++) {
            newChildren[i] = this.children[i];
        }
        newChildren[this.children.length] = child;
        this.children = newChildren;
    }
    public void removeChild(GomoryHuCutNode child) {
        GomoryHuCutNode[] newChildren = null;
        for (int i = 0; i < this.children.length; i++) {
            if(this.children[i] == child) {
                newChildren = new GomoryHuCutNode[this.children.length - 1];
                for (int j = 0; j < i; j++) {
                    newChildren[j] = this.children[j];
                }
                for (int j = i + 1; j < this.children.length; j++) {
                    newChildren[j-1] = this.children[j];
                }
            }
        }
        this.children = newChildren;
    }

    public void print(int deep) {
        if(deep == 0)
            System.out.println("\n============");
        String tab = "";
        for(int i = 0; i < deep; i++) {
            tab += "\t";
        }

        System.out.println(tab + "Weight: " + weight);
        System.out.print(tab + "Vertexes: ");
        for(int vertex: vertex)
            System.out.print(convertVertex(vertex) + " ");
        System.out.println();
        System.out.print(tab + "Children: ");
        if(children != null) {
            if(children.length == 1) {
                if (children[0] == null)
                    System.out.println("no children;");
            }
            System.out.println();
            for(GomoryHuCutNode child: children) {
                if(child != null)
                    child.print(deep + 1);
            }
        }
        if(deep == 0)
            System.out.println("============\n");
    }

    private char convertVertex(int vertex) {
        return (char)(vertex + 'a');
    }

    @Override
    public String toString() {

        return "GomoryHuCutNode{" + "vertex=" + vertex + ", weight=" + weight + ", parent=" + parent + ", children=" + children + '}';
    }
}
