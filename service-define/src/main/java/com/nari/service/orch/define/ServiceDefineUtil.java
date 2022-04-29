package com.nari.service.orch.define;

import com.nari.service.orch.define.nodes.StartNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDefineUtil {
    public static final ServiceDefine fromServiceSource(ServiceSource serviceSource) {
        ServiceDefine serviceDefine = new ServiceDefine();
        serviceDefine.setId(serviceSource.getId());
        serviceDefine.setName(serviceSource.getName());
        serviceDefine.setServiceId(serviceSource.getServiceId());
        Map<String, Node> nodeMap = new HashMap<>();
        for (Node node : serviceSource.getNodeList()) {
            nodeMap.put(node.getId(), node);
        }
        Map<String, List<LineNode>> lineNodesMap = new HashMap<>();
        for (Line line : serviceSource.getLineList()) {
            lineNodesMap.putIfAbsent(line.getFrom(), new ArrayList<>());
            LineNode lineNode = new LineNode(line, nodeMap.get(line.getTo()));
            lineNodesMap.get(line.getFrom()).add(lineNode);
        }
        for (Node node : serviceSource.getNodeList()) {
            node.setLineNodes(lineNodesMap.get(node.getId()));
            if (node instanceof StartNode) {
                serviceDefine.setStartNode(node);
            }
        }
        return serviceDefine;
    }
}
