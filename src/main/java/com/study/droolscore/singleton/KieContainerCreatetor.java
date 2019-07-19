//package com.study.droolscore.singleton;
//
//import com.study.droolscore.utils.SpringUtils;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieRepository;
//import org.kie.api.runtime.KieContainer;
//import org.kie.internal.io.ResourceFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//import java.io.IOException;
//
///**
// * com.study.droolscore.singleton
// *
// * @author jimmy
// * @date 2019-07-19
// */
//public class KieContainerCreatetor {
//
//    private static volatile KieContainer kieContainer;
//
//    public static KieContainer getInstance() {
//        if(kieContainer==null){
//            KieServices kieServices = SpringUtils.getBean("kieServices");
//            KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
//            for (Resource file : getRuleFiles()) {
//                kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
//            }
//            return kieFileSystem;
//            final KieRepository kieRepository = kieServices().getRepository();
//            kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
//            KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem());
//            kieBuilder.buildAll();
//            return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
//        }
//    }
//
//    private Resource[] getRuleFiles() throws IOException {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.drl");
//    }
//
//}
