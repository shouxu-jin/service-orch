package com.nari.service.orch.serviceserver.engine.executors;

import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.nodes.ResultNode;
import com.nari.service.orch.serviceserver.util.FreemarkerUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.nari.service.orch.serviceserver.util.Constants.RESULT;

@Service
public class ResultNodeExecutor implements NodeExecutor {

    @Override
    public boolean support(Node node) {
        return node instanceof ResultNode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) throws Exception {
        ResultNode resultNode = (ResultNode) node;
        context.put(RESULT, FreemarkerUtil.process(resultNode.getTemplate(), context));
    }

    @Override
    public Node chooseNext(Node current) {
        return current.getLineNodes().get(0).getNode();
    }
}
