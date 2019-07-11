package com.study.droolscore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
    //购买清单
    private final List<Food> buyList;
    //总价
    private BigDecimal totalMoney;
    //创建时间
    private Date createTime;
    //规则链
    private List<String> rules;
    //剩余食物
    private List<Food> leftList;

    public ShoppingCar(List<Food> buyList) {
        this.createTime = new Date();
        this.totalMoney = BigDecimal.ZERO;
        this.buyList = Collections.unmodifiableList(buyList);
        this.rules = new ArrayList<>();
        this.leftList = buyList;
    }

    public List<Food> getBuyList() {
        return buyList;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
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

    public List<Food> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<Food> leftList) {
        this.leftList = leftList;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "buyList=" + buyList +
                ", totalMoney=" + totalMoney +
                ", createTime=" + createTime +
                ", rules=" + rules +
                ", leftList=" + leftList +
                '}';
    }

    public boolean test(){
        System.out.println(toString());
        return true;
    }
}
