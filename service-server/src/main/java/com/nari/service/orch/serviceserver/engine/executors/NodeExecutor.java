package com.nari.service.orch.serviceserver.engine.executors;

import com.nari.service.orch.define.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface NodeExecutor {
    boolean support(Node node);

    void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) throws Exception;

    Node chooseNext(Node current);
}
