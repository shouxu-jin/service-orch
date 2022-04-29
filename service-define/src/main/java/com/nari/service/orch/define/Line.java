package com.nari.service.orch.define;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nari.service.orch.define.lines.ConditionLine;
import com.nari.service.orch.define.lines.DirectLine;
import com.nari.service.orch.define.lines.LoopLine;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DirectLine.class, name = "direct"),
        @JsonSubTypes.Type(value = ConditionLine.class, name = "condition"),
        @JsonSubTypes.Type(value = LoopLine.class, name = "loop"),
})
public class Line {
    private String from;
    private String to;
    private String type;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
