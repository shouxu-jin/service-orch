package com.nari.service.orch.define.lines;

import com.nari.service.orch.define.Line;

public class LoopLine extends Line {
    private String condition;
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
