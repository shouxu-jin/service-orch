package com.nari.service.orch.serviceserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.ServiceDefine;
import com.nari.service.orch.serviceserver.loader.ServiceDefineLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ServiceController {
    @Resource
    private ServiceDefineLoader serviceDefineLoader;

    @RequestMapping("/serve/view")
    public void serve(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String serviceId = request.getParameter("serviceId");
        ServiceDefine serviceDefine = serviceDefineLoader.load(serviceId);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(serviceDefine));
    }
}
