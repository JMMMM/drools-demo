package com.study.droolscore.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * com.study.droolscore.domain
 * <p>
 * 购物车类
 *
 * @author jimmy
 * @date 2019-07-05
 */
public class ShoppingCar {
    private List<Food> buyList;
    private Double totalMoney;
    private Date createTime;
    private List<String> rules;

    public ShoppingCar() {
        this.createTime = new Date();
        this.rules = new ArrayList<>();
    }

    public ShoppingCar(List<Food> buyList) {
        this();
        this.buyList = buyList;
    }

    public List<Food> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<Food> buyList) {
        this.buyList = buyList;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }
}
