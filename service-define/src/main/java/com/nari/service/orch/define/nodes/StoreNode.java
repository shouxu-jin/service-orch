package com.nari.service.orch.define.nodes;

import com.nari.service.orch.define.Node;

public class StoreNode extends Node {
    private String storeName;
    private String expression;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
