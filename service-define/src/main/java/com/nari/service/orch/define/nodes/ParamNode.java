package com.nari.service.orch.define.nodes;

import com.nari.service.orch.define.Node;

public class ParamNode extends Node {
    private String paramName;
    private String expression;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
