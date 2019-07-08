package com.study.droolscore.domain;

import java.util.List;

/**
 * com.study.droolscore.domain
 * <p>
 * 套餐规则模版类
 *
 * @author jimmy
 * @date 2019-07-05
 */
public class TemplateForBillRules {
    private List<String> fooPromotion;
    private Double pricePromotion;
    private Integer buyListSize;
    private String templateName;

    public TemplateForBillRules() {
    }

    public TemplateForBillRules(List<String> fooPromotion, Double pricePromotion, Integer buyListSize, String templateName) {
        this.fooPromotion = fooPromotion;
        this.pricePromotion = pricePromotion;
        this.buyListSize = buyListSize;
        this.templateName = templateName;
    }

    public List<String> getFooPromotion() {
        return fooPromotion;
    }

    public void setFooPromotion(List<String> fooPromotion) {
        this.fooPromotion = fooPromotion;
    }

    public Double getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(Double pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public Integer getBuyListSize() {
        return buyListSize;
    }

    public void setBuyListSize(Integer buyListSize) {
        this.buyListSize = buyListSize;
    }


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
