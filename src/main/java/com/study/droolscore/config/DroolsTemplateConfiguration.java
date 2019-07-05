package com.study.droolscore.config;

import com.study.droolscore.domain.TemplateForBillRules;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * com.study.droolscore.config
 *
 * @author jimmy
 * @date 2019-07-05
 */
@Configuration
public class DroolsTemplateConfiguration {
    private static final String RULES_PATH = "rules/";

    @Bean
    public KieSession kieSession2() throws IOException {
        KieHelper kieHelper = new KieHelper();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
        List<TemplateForBillRules> templates = templates();
        for (Resource resource : getRuleFiles()) {
            String drl = null;
            if (resource.getFilename().endsWith("drt")) {
                drl = objectDataCompiler.compile(templates, resource.getInputStream());
                kieHelper.addContent(drl, ResourceType.DRL);
            } else {
                kieHelper.addResource(ResourceFactory.newClassPathResource(RULES_PATH + resource.getFilename(), "UTF-8"));
            }
        }
        return kieHelper.build().newKieSession();
    }

    private List<TemplateForBillRules> templates() {
        List<TemplateForBillRules> templates = new ArrayList<>();
        TemplateForBillRules templateForBillRules = new TemplateForBillRules(Arrays.asList("\"巨无霸\"", "\"可乐\"", "\"薯条\""), 17D, 3);
        templates.add(templateForBillRules);

        TemplateForBillRules templateForBillRules2 = new TemplateForBillRules(Arrays.asList("\"巨无霸\"", "\"可乐\""), 10D, 2);
        templates.add(templateForBillRules2);
        return templates;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
}
