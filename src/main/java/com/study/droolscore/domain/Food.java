package com.study.droolscore.domain;

/**
 * com.study.droolscore.food
 *
 * 具体食品类
 * @author jimmy
 * @date 2019-07-05
 */
public class Food {
    private String name;
    private double price;

    public Food() {
    }

    public Food(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
