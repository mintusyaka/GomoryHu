package edu.app;

import edu.builders.GomoryCutTreeBuilder;
import edu.models.GomoryHuCutNode;
import edu.models.Graph;
import edu.services.GomoryHuCutTools;

import java.util.Arrays;

public class GomoryHuAlgorithmApp {
    public static void main(String[] args) {
        Graph graph = new Graph(new int[][]{
                {0, 5, 9, 5, 0, 8, 0},
                {5, 0, 0, 0, 3, 0, 0},
                {9, 0, 0, 0, 2, 6, 4},
                {5, 0, 0, 0, 0, 7, 8},
                {0, 3, 2, 0, 0, 4, 0},
                {8, 0, 6, 7, 4, 0, 0},
                {0, 0, 4, 8, 0, 0, 0}
        });


        GomoryCutTreeBuilder builder = new GomoryCutTreeBuilder();
        builder.addVertexes(new int[]{0,1,2,3,4,5,6});
        builder.addChild(null);
        GomoryHuCutNode root = builder.build();


        while(root.getVertex().length > 1) {
            GomoryHuCutTools.getMinCut(GomoryHuCutTools.findMaxIn(graph, root), graph, root);
        }
        root.print(0);
    }
}
