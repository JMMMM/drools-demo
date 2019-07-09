package com.study.droolscore.config;

import com.study.droolscore.domain.TemplateForBillRules;
import com.study.droolscore.service.ComboTemplateService;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
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

    @Autowired
    private ComboTemplateService comboTemplateService;

    @Bean
    public KieSession kieSession2() throws IOException {
        KieHelper kieHelper = new KieHelper();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
        List<TemplateForBillRules> templates = comboTemplateService.createTemplate();
        for (Resource resource : getRuleFiles()) {
            String drl;
            if (resource.getFilename().endsWith("drt")) {
                drl = objectDataCompiler.compile(templates, resource.getInputStream());
                kieHelper.addContent(drl, ResourceType.DRL);
            } else {
                kieHelper.addResource(ResourceFactory.newClassPathResource(RULES_PATH + resource.getFilename(), "UTF-8"));
            }
        }
        return kieHelper.build().newKieSession();
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
}
