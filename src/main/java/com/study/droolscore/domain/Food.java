package com.study.droolscore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * com.study.droolscore.food
 * <p>
 * 具体食品类
 *
 * @author jimmy
 * @date 2019-07-05
 */
@Entity
@Table(name = "food")
public class Food {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;

    public Food() {
    }

    public Food(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
