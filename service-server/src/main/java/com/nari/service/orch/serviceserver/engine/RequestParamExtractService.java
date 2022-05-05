package com.nari.service.orch.serviceserver.engine;

import com.nari.service.orch.serviceserver.util.Constants;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;

@Service
public class RequestParamExtractService {
    private static final Logger logger = LoggerFactory.getLogger(RequestParamExtractService.class);
    public void extractParam(HttpServletRequest request, Map<String, Object> context) {
        extractRequestParam(request, context);
        extractRequestHeader(request, context);
        extractRequestBody(request, context);
    }

    private void extractRequestBody(HttpServletRequest request, Map<String, Object> context) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String requestBody = IOUtils.toString(reader);
            context.put(Constants.REQUEST_BODY, requestBody);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private void extractRequestHeader(HttpServletRequest request, Map<String, Object> context) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                context.put(headerName, request.getParameter(headerName));
            }
        }
    }

    private void extractRequestParam(HttpServletRequest request, Map<String, Object> context) {
        Enumeration<String> parameterNames = request.getParameterNames();
        if (null != parameterNames) {
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                context.put(paramName, request.getParameter(paramName));
            }
        }
    }
}
