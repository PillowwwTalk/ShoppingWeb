package com.zhj.pojo;

import java.math.BigDecimal;

public class HistoryItem {
    private Integer id;
    private String name;
    private String shop;
    private BigDecimal price;
    public HistoryItem(Integer id, String name, String shop, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.price = price;
    }
    public Integer getId() { return id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
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
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shop=" + shop +
                ", price=" + price+
                '}';
    }
}
