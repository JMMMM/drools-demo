//package com.study.droolscore.singleton;
//
//import com.study.droolscore.domain.TemplateForBillRules;
//import com.study.droolscore.service.ComboTemplateService;
//import com.study.droolscore.utils.SpringUtils;
//import org.drools.template.ObjectDataCompiler;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieContainer;
//import org.kie.internal.io.ResourceFactory;
//import org.kie.internal.utils.KieHelper;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * com.study.droolscore.singln
// *
// * @author jimmy
// * @date 2019-07-19
// */
//public class KieSessionCreatetor {
//    private static final String RULES_PATH = "rules/";
//
//    private static volatile KieContainer kieContainer;
//
//    public static KieContainer getInstance() {
//        if (kieContainer == null) {
//            synchronized (KieSessionCreatetor.class) {
//                reCreate();
//            }
//        }
//        return null;
//    }
//
//    public static KieContainer reCreate() {
//        ComboTemplateService comboTemplateService = SpringUtils.getBean("comboTemplateService");
//        KieHelper kieHelper = new KieHelper();
//        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
//        List<TemplateForBillRules> templates = comboTemplateService.createTemplate();
//        try {
//            for (Resource resource : getRuleFiles()) {
//                String drl = null;
//                if (resource.getFilename().endsWith("drt")) {
//                    try {
//                        drl = objectDataCompiler.compile(templates, resource.getInputStream());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    kieHelper.addContent(drl, ResourceType.DRL);
//                } else {
//                    kieHelper.addResource(ResourceFactory.newClassPathResource(RULES_PATH + resource.getFilename(), "UTF-8"));
//                }
//            }
//            kieContainer = kieHelper.build();
//            return kieContainer;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static Resource[] getRuleFiles() throws IOException {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
//    }
//
//}
