package edu.builders;

import edu.models.GomoryHuCutNode;

public class GomoryCutTreeBuilder {
    GomoryHuCutNode root;

    public GomoryCutTreeBuilder() {
        this.root = new GomoryHuCutNode(new int[]{}, 0, null, new GomoryHuCutNode[]{});
    }

    public void addVertexes(int[] vertexes) {
        root.setVertex(vertexes);
    }

    public void addChild(GomoryHuCutNode node) {
        root.addChild(node);
        if(node != null) {
            node.setParent(root);
        }
    }
    public void setChild(GomoryHuCutNode[] nodes) {
        root.setChildren(nodes);
        if(nodes != null) {
            for(GomoryHuCutNode node: nodes) {
                node.setParent(root);
            }
        }
    }

    public GomoryHuCutNode build() {
        GomoryHuCutNode temp = root;
        root = new GomoryHuCutNode(new int[]{}, 0, null, new GomoryHuCutNode[]{});
        return temp;
    }
}
