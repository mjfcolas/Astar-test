package com.manu.pathfinder.pathfinder;

public class NodeStatus {

    private Node parent;

    private final Node underlyingNode;
    private int g = 0;

    public NodeStatus(final Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    public int getF(Node destNode) {
        return this.underlyingNode.getH(destNode) + this.g;
    }

    public void setG(final int g) {
        this.g = g;
    }

    public int getG() {
        return g;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public Node getUnderlyingNode() {
        return underlyingNode;
    }
}
