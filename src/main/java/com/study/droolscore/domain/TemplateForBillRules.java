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
    private BigDecimal comboPrice;
    private Integer buyListSize;
    private String templateName;
    //foods.price.sum - comboPrice
    private int promotionPrice;

    public TemplateForBillRules() {
    }

    public TemplateForBillRules(List<Food> foods, BigDecimal comboPrice, Integer buyListSize, String templateName) {
        this.foods = foods;
        this.foodNames = foods.stream().map((x) -> "\"" + x.getName() + "\"").collect(Collectors.toList());
        this.comboPrice = comboPrice;
        this.buyListSize = buyListSize;
        this.templateName = templateName;
        this.promotionPrice = this.foods.stream().map(Food::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(BigDecimal.valueOf(100)).intValue();
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

    public BigDecimal getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(BigDecimal comboPrice) {
        this.comboPrice = comboPrice;
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

    public int getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(int promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}
