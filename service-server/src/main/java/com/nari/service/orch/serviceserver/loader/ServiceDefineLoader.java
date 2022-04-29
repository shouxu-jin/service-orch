package com.nari.service.orch.serviceserver.loader;

import com.nari.service.orch.define.ServiceDefine;

public interface ServiceDefineLoader {
    ServiceDefine load(String serviceId);
}
