package com.study.droolscore.domain;

import java.util.List;

/**
 * com.study.droolscore.domain
 *
 * @author jimmy
 * @date 2019-07-05
 */
public class TemplateForBillRules {
    private List<String> fooPromotion;
    private Double pricePromotion;
    private Integer buyListSize;

    public TemplateForBillRules() {
    }

    public TemplateForBillRules(List<String> fooPromotion, Double pricePromotion, Integer buyListSize) {
        this.fooPromotion = fooPromotion;
        this.pricePromotion = pricePromotion;
        this.buyListSize = buyListSize;
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
}
