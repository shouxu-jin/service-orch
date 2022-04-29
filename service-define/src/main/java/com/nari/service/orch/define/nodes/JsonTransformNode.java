package com.nari.service.orch.define.nodes;

import com.nari.service.orch.define.Node;

public class JsonTransformNode extends Node {
    private String transformFrom;
    private String transformTo;

    public String getTransformFrom() {
        return transformFrom;
    }

    public void setTransformFrom(String transformFrom) {
        this.transformFrom = transformFrom;
    }

    public String getTransformTo() {
        return transformTo;
    }

    public void setTransformTo(String transformTo) {
        this.transformTo = transformTo;
    }
}
