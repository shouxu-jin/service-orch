package com.nari.service.orch.serviceserver.engine;

import com.nari.service.orch.define.ServiceDefine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ServiceExecuteEngine {
    void execute(HttpServletRequest request, HttpServletResponse response, ServiceDefine serviceDefine, Map<String, Object> context);
}
