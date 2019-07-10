package com.study.droolscore.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.study.droolscore.domain
 * <p>
 * 套餐规则模版类
 *
 * @author jimmy
 * @date 2019-07-05
 */
public class TemplateForBillRules {
    private List<Food> foods;
    private List<String> foodNames;
    private BigDecimal pricePromotion;
    private Integer buyListSize;
    private String templateName;

    public TemplateForBillRules() {
    }

    public TemplateForBillRules(List<Food> foods, BigDecimal pricePromotion, Integer buyListSize, String templateName) {
        this.foods = foods;
        this.foodNames = foods.stream().map((x) -> "\"" + x.getName() + "\"").collect(Collectors.toList());
        this.pricePromotion = pricePromotion;
        this.buyListSize = buyListSize;
        this.templateName = templateName;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<String> getFoodNames() {
        return foodNames;
    }

    public void setFoodNames(List<String> foodNames) {
        this.foodNames = foodNames;
    }

    public BigDecimal getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(BigDecimal pricePromotion) {
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
