package com.nari.service.orch.serviceserver.util;

import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Map;

public class FreemarkerUtil {
    private static final Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
    public static final String JSON = "json";
    public static final String TEMPLATE_NAME = "templateName";

    private static Configuration conf = configuration();
    private static TemplateMethodModelEx jsonTemplateMethodModelEx = arguments -> {
        Object obj = arguments.get(0);
        if (obj instanceof SimpleHash) {
            SimpleHash simpleHash = (SimpleHash) obj;
            try {
                Field map = SimpleHash.class.getDeclaredField("map");
                map.setAccessible(true);
                return new SimpleScalar(JsonUtil.toString(map.get(simpleHash)));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("", e);
            }
        }
        if (obj instanceof SimpleSequence) {
            SimpleSequence simpleSequence = (SimpleSequence) obj;
            try {
                Field list = SimpleSequence.class.getDeclaredField("list");
                list.setAccessible(true);
                return new SimpleScalar(JsonUtil.toString(list.get(simpleSequence)));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("", e);
            }
        }
        return obj;
    };

    private static Configuration configuration() {
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        DefaultObjectWrapper objectWrapper = new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        conf.setObjectWrapper(objectWrapper);
        conf.setDefaultEncoding("UTF-8");
        return conf;
    }

    public static final String process(String template, Map<String, Object> context) throws IOException, TemplateException {
        if (!StringUtils.hasLength(template)) {
            return null;
        }
        context.put(JSON, jsonTemplateMethodModelEx);
        Writer out = new StringWriter();
        new Template(TEMPLATE_NAME, new StringReader(template), conf).process(context, out);
        return out.toString();
    }
}
