package com.nari.service.orch.serviceserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.ServiceDefine;
import com.nari.service.orch.serviceserver.engine.ServiceExecuteEngine;
import com.nari.service.orch.serviceserver.loader.ServiceDefineLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class ServiceController {
    @Resource
    private ServiceDefineLoader serviceDefineLoader;
    @Resource
    private ServiceExecuteEngine serviceExecuteEngine;

    @RequestMapping("/serve/view/{serviceId}")
    public void view(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceId) throws Exception {
        ServiceDefine serviceDefine = serviceDefineLoader.load(serviceId);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(serviceDefine));
    }

    @RequestMapping("/serve/execute/{serviceId}")
    public void execute(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceId) throws Exception {
        ServiceDefine serviceDefine = serviceDefineLoader.load(serviceId);
        response.setContentType("text/json;charset=UTF-8");
        serviceExecuteEngine.execute(request, response, serviceDefine, new HashMap<>());
    }
}
