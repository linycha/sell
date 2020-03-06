package com.sell.modules.store.vo;

import java.io.Serializable;

/**
 * @author linyuc
 * @date 2020/3/1 12:09
 */
public class Cart implements Serializable {
    //num":1,"name":"饿了么宫廷套餐","logoImg":"http://sell.…7c5-44ea-ae4e-7c4797ef9993.png","sellPrice":155}]"}cart: "[{"num":1,"name":"饿了么宫廷套餐","logoImg":"http://sell.file.com/product/0372e42e-f509-43f5-a27b-5641c9eeb270.png","sellPrice":99},{"num":1,"name":"春节全家团员分享桶1",
    // "logoImg":"http://sell.file.com/product/7ad86273-07c5-44ea-ae4e-7c4797ef9993.png","sellPrice":155}]"__proto__: Object
    private String id;
    private String name;
    private String logoImg;
    private Integer num;
    private String sellPrice;

    public Cart() {
    }

    public Cart(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logoImg='" + logoImg + '\'' +
                ", num='" + num + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                '}';
    }
}
