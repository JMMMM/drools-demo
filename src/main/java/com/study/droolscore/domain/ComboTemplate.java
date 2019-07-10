package com.study.droolscore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * com.study.droolscore.domain
 *
 * @author jimmy
 * @date 2019-07-09
 */
@Entity
@Table(name = "combo_template")
public class ComboTemplate {
    @Id
    private Integer id;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "combo_price")
    private BigDecimal comboPrice;

    @Column(name = "combo_food_num")
    private Integer comboFoodNum;

    @Column(name = "food_ids")
    private String foodIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public BigDecimal getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(BigDecimal comboPrice) {
        this.comboPrice = comboPrice;
    }

    public Integer getComboFoodNum() {
        return comboFoodNum;
    }

    public void setComboFoodNum(Integer comboFoodNum) {
        this.comboFoodNum = comboFoodNum;
    }

    public String getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(String foodIds) {
        this.foodIds = foodIds;
    }
}
