package com.nari.service.orch.serviceserver.engine;

import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.ServiceDefine;
import com.nari.service.orch.serviceserver.engine.executors.NodeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class ServiceExecuteEngineImpl implements ServiceExecuteEngine {
    private static final Logger logger = LoggerFactory.getLogger(ServiceExecuteEngineImpl.class);
    @Resource
    private RequestParamExtractService requestParamExtractService;
    @Resource
    private List<NodeExecutor> nodeExecutors;

    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceDefine serviceDefine, Map<String, Object> context) {
        requestParamExtractService.extractParam(request, context);
        Node current = serviceDefine.getStartNode();
        while (null != current) {
            for (NodeExecutor nodeExecutor : nodeExecutors) {
                if (nodeExecutor.support(current)) {
                    try {
                        nodeExecutor.execute(request, response, current, context);
                    } catch (Exception e) {
                        logger.error(String.format("execute failed for service %s", serviceDefine.getServiceId()), e);
                        return;
                    }
                    Node next = nodeExecutor.chooseNext(current);
                    current = next;
                    break;
                }
            }
        }
    }
}
