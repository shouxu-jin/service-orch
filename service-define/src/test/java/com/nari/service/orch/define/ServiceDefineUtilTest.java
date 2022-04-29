package com.nari.service.orch.define;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class ServiceDefineUtilTest {
    @Test
    public void testSerializable() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = ServiceDefineTest.class.getClassLoader().getResource("absolute.json");
        ServiceSource serviceSource = objectMapper.readerFor(ServiceSource.class).readValue(resource);
        ServiceDefine serviceDefine = ServiceDefineUtil.fromServiceSource(serviceSource);
        String expected = IOUtils.toString(ServiceDefineTest.class.getClassLoader().getResource("absolute-define.json"), "UTF-8");
        Assert.assertEquals(expected.replaceAll("\r|\n", ""), objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(serviceDefine).replaceAll("\r|\n", ""));
    }
}
