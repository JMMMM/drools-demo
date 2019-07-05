package com.study.droolscore.domain;

import java.util.List;

/**
 * com.study.droolscore.domain
 *
 * @author jimmy
 * @date 2019-07-05
 */
public class ShoppingCar {
    private List<Food> buyList;
    private Double totalMoney;

    public ShoppingCar() {
    }

    public ShoppingCar(List<Food> buyList) {
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
}
