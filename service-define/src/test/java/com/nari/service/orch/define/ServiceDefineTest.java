package com.nari.service.orch.define;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

class ServiceDefineTest {
    @Test
    public void gg() throws Exception {

        Object o = new ObjectMapper().readerFor(ServiceSource.class).readValue(ServiceDefineTest.class.getResource(""));
    }
}
