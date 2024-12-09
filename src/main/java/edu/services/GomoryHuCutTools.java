package edu.services;

import edu.models.GomoryHuCut;
import edu.models.GomoryHuCutNode;
import edu.models.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class GomoryHuCutTools {
    public static int getMinCut(int vertex, Graph graph, GomoryHuCutNode root) {
        int minCutValueIdx = 0;
        int minCutValue = Integer.MAX_VALUE;
        GomoryHuCutNode minNode = null;
        int[] minVertexes = null;

        for(GomoryHuCutNode node: root.getChildren()) {
            int[] vertexes = GomoryHuCutNodeTools.getAllVertexes(new Vector<>(), new GomoryHuCutNode[]{node});

            List<List<Integer>> combinations = new ArrayList<>();
            generateCombinations(vertexes, 0, new ArrayList<>(), combinations, vertex);

            for (List<Integer> combination : combinations) {
                int cutValue = getCutValue(combination.stream().mapToInt(Integer::intValue).toArray(), graph);
                if(minCutValue > cutValue) {
                    minCutValueIdx = combinations.indexOf(combination);
                    minCutValue = cutValue;
                    minNode = node;
                    minVertexes = combination.stream().mapToInt(Integer::intValue).toArray();
                }
            }
        }

        GomoryHuCutNode newNode;
        if(minNode == null) {
            newNode = new GomoryHuCutNode(new int[]{vertex}, minCutValue, root, new GomoryHuCutNode[]{null});
        } else {
            newNode = new GomoryHuCutNode(new int[]{vertex}, minCutValue, minNode.getParent(), new GomoryHuCutNode[]{minNode});
            minNode.setParent(newNode);
            root.removeChild(minNode);
        }
        root.addChild(newNode);
        root.removeVertex(vertex);
        newNode.setWeight(minCutValue);

        System.out.println(minCutValue);
        System.out.println(Arrays.toString(minVertexes));

        return getCutValue(minVertexes, graph);

    }

    public static int findMaxIn(Graph graph, GomoryHuCutNode root) {
        int[] vertexes = GomoryHuCutNodeTools.getAllVertexes(new Vector<>(), new GomoryHuCutNode[]{root});

        int[][] matrix = graph.getMatrix();

        boolean[][] visited = graph.getUsedForSearchMask();

        int maxWayInVertex = 0;
        int maxWay = Integer.MIN_VALUE;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                final int finalJ = j;
                if(i != j && matrix[i][j] > maxWay && !visited[i][j] && !visited[j][i] && Arrays.stream(vertexes).noneMatch(v -> v == finalJ)) {
                    maxWay = matrix[i][j];
                    maxWayInVertex = j;
                    visited[i][j] = true;
                    visited[j][i] = true;
                }
            }
        }

        return maxWayInVertex;
    }

    private static int getCutValue(int[] vertexes, Graph graph) {
        int value = 0;

        for(int vertex: vertexes) {
            for(int i = 0; i < graph.getMatrix().length; i++) {
                final int finalI = i;
                if(Arrays.stream(vertexes).noneMatch(v -> v == finalI)) {
                    value += graph.getMatrix()[vertex][i];
                }
            }
        }

        return value;
    }

    private static void generateCombinations(int[] array, int index, List<Integer> current, List<List<Integer>> combinations, int requiredElement) {
        // Include required element if not already present
        if (!current.contains(requiredElement)) {
            current.add(requiredElement);
        }

        if (index == array.length) {
            // Ensure the required element is in the combination before adding it
            if (current.contains(requiredElement)) {
                combinations.add(new ArrayList<>(current));
            }
            return;
        }

        // Include the current element if it's not already in the current list
        if (!current.contains(array[index])) {
            current.add(array[index]);
            generateCombinations(array, index + 1, current, combinations, requiredElement);

            // Remove the current element for the next potential combination
            current.remove(current.size() - 1);
        }

        // Recursion without the current element
        generateCombinations(array, index + 1, current, combinations, requiredElement);
    }

}
