package com.nari.service.orch.serviceserver.engine.executors;

import com.nari.service.orch.define.Node;
import com.nari.service.orch.define.nodes.HttpNode;
import com.nari.service.orch.serviceserver.util.FreemarkerUtil;
import com.nari.service.orch.serviceserver.util.JsonUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpNodeExecutor implements NodeExecutor, InitializingBean {

    public static final String SUFFIX = "Json";
    private RestTemplate restTemplate;

    @Override
    public boolean support(Node node) {
        return node instanceof HttpNode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Node node, Map<String, Object> context) throws Exception {
        HttpNode httpNode = (HttpNode) node;
        String url = FreemarkerUtil.process(httpNode.getUrl(), context);
        String body = FreemarkerUtil.process(httpNode.getBody(), context);
        String headers = FreemarkerUtil.process(httpNode.getHeaders(), context);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (StringUtils.hasLength(headers)) {
            for (String header : headers.split(";")) {
                httpHeaders.add(header.split(":")[0], header.split(":")[1]);
            }
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);
        String result = restTemplate.postForEntity(url, httpEntity, String.class).getBody();
        context.put(httpNode.getResultName(), result);
        context.put(httpNode.getResultName() + SUFFIX, JsonUtil.parse(result, Map.class));
    }

    @Override
    public Node chooseNext(Node current) {
        return current.getLineNodes().get(0).getNode();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
}
