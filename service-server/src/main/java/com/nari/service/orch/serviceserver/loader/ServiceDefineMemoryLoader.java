package com.nari.service.orch.serviceserver.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.ServiceDefine;
import com.nari.service.orch.define.ServiceDefineUtil;
import com.nari.service.orch.define.ServiceSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceDefineMemoryLoader implements ServiceDefineLoader, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDefineMemoryLoader.class);

    private Map<String, ServiceDefine> serviceDefines = new HashMap<>();

    @Override
    public ServiceDefine load(String serviceId) {
        return serviceDefines.get(serviceId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        for (String filename : new File(rootPath).list()) {
            if (filename.endsWith(".json")) {
                try {
                    loadFile(filename);
                } catch (IOException e) {
                    logger.error(String.format("load service %s failed", filename), e);
                }
            }
        }
    }

    private void loadFile(String filename) throws java.io.IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(filename);
        ServiceSource serviceSource = new ObjectMapper().readerFor(ServiceSource.class).readValue(resource);
        ServiceDefine serviceDefine = ServiceDefineUtil.fromServiceSource(serviceSource);
        serviceDefines.put(serviceDefine.getServiceId(), serviceDefine);
    }
}
