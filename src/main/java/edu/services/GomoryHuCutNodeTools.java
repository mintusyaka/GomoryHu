package edu.services;

import edu.models.GomoryHuCutNode;

import java.util.Vector;

public class GomoryHuCutNodeTools {
    public static int[] getAllVertexes(Vector<Integer> vertexes, GomoryHuCutNode[] nodes) {
        if(nodes != null)
            for(GomoryHuCutNode node: nodes)
            {
                if(node != null && node.getVertex() != null && node.getVertex().length == 1) {
                    vertexes.add(node.getVertex()[0]);
                    if(node.getChildren() != null)
                        getAllVertexes(vertexes, node.getChildren());
                }
                else {
                    if(node != null && node.getChildren() != null)
                        getAllVertexes(vertexes, node.getChildren());
                }
            }

        return vertexes.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void addNode(int[] vertexes, GomoryHuCutNode root) {
        for(int vertex: vertexes) {
            if(root != null) {
                if(root.getChildren() != null) {
                    for(GomoryHuCutNode node: root.getChildren()) {
                        if(node.getVertex() != null && node.getVertex().length == 1) {
                            if(node.getVertex()[0] == vertex) {
                                return;
                            }
                        }
                    }
                    addNode(vertexes, root.getChildren()[0]);
                }
            }
        }
    }
}
