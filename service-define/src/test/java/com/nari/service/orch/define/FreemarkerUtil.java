package com.nari.service.orch.define;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class FreemarkerUtil {
    private static Configuration conf = configuration();

    private static Configuration configuration() {
        Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        conf.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        conf.setDefaultEncoding("UTF-8");
        return conf;
    }

    public static final String process(String template, Object input) throws IOException, TemplateException {
        Writer out = new StringWriter();
        new Template("templateName", new StringReader(template), conf).process(input, out);
        return out.toString();
    }
}
