package com.nari.service.orch.define;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.nodes.ResultNode;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.HashMap;

public class ServiceDefineTest {

    @Test
    public void testSerializable() throws Exception {
        URL resource = ServiceDefineTest.class.getClassLoader().getResource("absolute.json");
        ServiceSource serviceSource = new ObjectMapper().readerFor(ServiceSource.class).readValue(resource);
        ResultNode resultNode = (ResultNode) serviceSource.getNodeList().get(2);
        System.out.println(resultNode.getTemplate());
        Assert.assertEquals(resultNode.getTemplate(), FreemarkerUtil.process(resultNode.getTemplate(), new HashMap<>()));
    }

}
