package com.study.droolscore.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * com.study.droolscore.config
 *
 * @author jimmy
 * @date 2019-07-05
 */
@Configuration
public class DroolsAutoConfiguration {
    private static final String RULES_PATH = "rules/";

    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.drl");
    }

    /**
     * KieContainer就是一个KieBase的容器，可以根据kmodule.xml 里描述的KieBase信息来获取具体的KieSession
     * @return
     * @throws IOException
     */
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    /**
     * 根据KieSession可以理解成一次规则创建一次session，也可以复用，但是内部的global对象也会共享
     * @return
     * @throws IOException
     */
    @Bean
    @ConditionalOnMissingBean(KieSession.class)
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    /*
     *  As http://docs.jboss.org/drools/release/6.2.0.CR1/drools-docs/html/ch.kie.spring.html
     *  mentions: Without the org.kie.spring.KModuleBeanFactoryPostProcessor bean definition,
     *  the kie-spring integration will not work
     */
    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

    /**
     * 这里本来就是单例模式
     * 根据各个kie.jar内部的kie.conf进行加载，这里有点像spi，但是加载方式他是自己写的。
     * 就是对kieService做基础的初始化操作
     */
    @Bean
    @ConditionalOnMissingBean(KieServices.class)
    public KieServices kieServices() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        return KieServices.Factory.get();
    }

}
