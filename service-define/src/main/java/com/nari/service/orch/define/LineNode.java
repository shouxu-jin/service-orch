package com.nari.service.orch.define;

public class LineNode {
    private Line line;
    private Node node;

    public LineNode() {
    }

    public LineNode(Line line, Node node) {
        this.line = line;
        this.node = node;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
