package com.nari.service.orch.define;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.nodes.EndNode;
import org.junit.Assert;
import org.junit.Test;

public class NodeTest {
    @Test
    public void gg() throws Exception {
        Object o = new ObjectMapper().readerFor(Node.class).readValue("{\"type\":\"endNode\"}".getBytes());
        Assert.assertEquals(o.getClass(), EndNode.class);
    }
}
