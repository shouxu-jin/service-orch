package com.nari.service.orch.serviceserver.engine.executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.nodes.EndNode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.nari.service.orch.serviceserver.util.Constants.RESULT;

@Service
public class EndNodeExecutor implements NodeExecutor {
    @Override
    public boolean support(Node node) {
        return node instanceof EndNode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) throws Exception {
        Object result = context.get(RESULT);
        if (null != result) {
            if (result instanceof String) {
                response.getWriter().write((String) result);
            } else {
                response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
            }
        }
    }

    @Override
    public Node chooseNext(Node current) {
        return null;
    }
}
