package com.nari.service.orch.serviceserver.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.ServiceDefine;
import com.nari.service.orch.define.ServiceDefineUtil;
import com.nari.service.orch.define.ServiceSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceDefineMemoryLoader implements ServiceDefineLoader, InitializingBean {
    private Map<String, ServiceDefine> serviceDefines = new HashMap<>();

    @Override
    public ServiceDefine load(String serviceId) {
        return serviceDefines.get(serviceId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        URL resource = ServiceDefineMemoryLoader.class.getClassLoader().getResource("absolute.json");
        ServiceSource serviceSource = new ObjectMapper().readerFor(ServiceSource.class).readValue(resource);
        ServiceDefine serviceDefine = ServiceDefineUtil.fromServiceSource(serviceSource);
        serviceDefines.put(serviceDefine.getServiceId(), serviceDefine);
    }
}
