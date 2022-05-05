package com.nari.service.orch.serviceserver.engine.executors;

import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.nodes.JsonTransformNode;
import com.nari.service.orch.serviceserver.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class JsonTransformExecutor implements NodeExecutor {
    @Override
    public boolean support(Node node) {
        return node instanceof JsonTransformNode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) throws Exception {
        JsonTransformNode jsonTransformNode = (JsonTransformNode) node;
        String from = jsonTransformNode.getTransformFrom();
        Object source = context.get(from);
        if (null != source) {
            if (source instanceof String) {
                context.put(jsonTransformNode.getTransformTo(), JsonUtil.parse((String) source, Map.class));
            } else {
                context.put(jsonTransformNode.getTransformTo(), JsonUtil.toString(source));
            }
        }
    }

    @Override
    public Node chooseNext(Node current) {
        return current.getLineNodes().get(0).getNode();
    }
}
