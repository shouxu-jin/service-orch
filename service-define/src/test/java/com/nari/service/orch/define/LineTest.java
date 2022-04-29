package com.nari.service.orch.define;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.lines.ConditionLine;
import com.nari.service.orch.define.nodes.EndNode;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {
    @Test
    public void gg() throws Exception {
        Object o = new ObjectMapper().readerFor(Line.class).readValue("{\"type\":\"condition\"}".getBytes());
        Assert.assertEquals(o.getClass(), ConditionLine.class);
    }
}
