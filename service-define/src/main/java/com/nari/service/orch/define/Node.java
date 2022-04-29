package com.nari.service.orch.define;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nari.service.orch.define.nodes.*;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StartNode.class, name = "start"),
        @JsonSubTypes.Type(value = EndNode.class, name = "end"),
        @JsonSubTypes.Type(value = ParamNode.class, name = "param"),
        @JsonSubTypes.Type(value = HttpNode.class, name = "http"),
        @JsonSubTypes.Type(value = ResultNode.class, name = "result"),
})
public class Node {
    private String id;
    private String name;
    private String type;
    private String left;
    private String top;
    private String ico;
    private List<LineNode> lineNodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public List<LineNode> getLineNodes() {
        return lineNodes;
    }

    public void setLineNodes(List<LineNode> lineNodes) {
        this.lineNodes = lineNodes;
    }
}

