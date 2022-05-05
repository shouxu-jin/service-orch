package com.nari.service.orch.serviceserver.engine.executors;

import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.nodes.StartNode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class StartNodeExecutor implements NodeExecutor {
    @Override
    public boolean support(Node node) {
        return node instanceof StartNode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) {

    }

    @Override
    public Node chooseNext(Node current) {
        return current.getLineNodes().get(0).getNode();
    }
}
