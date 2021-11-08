package com.sell.modules.store.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @date 2020/3/1 12:09
 */
@Data
public class Cart implements Serializable {
    private static final long serialVersionUID = -4748335868054393795L;

    //num":1,"name":"饿了么宫廷套餐","logoImg":"http://sell.…7c5-44ea-ae4e-7c4797ef9993.png","sellPrice":155}]"}cart: "[{"num":1,"name":"饿了么宫廷套餐","logoImg":"http://sell.file.com/product/0372e42e-f509-43f5-a27b-5641c9eeb270.png","sellPrice":99},{"num":1,"name":"春节全家团员分享桶1",
    // "logoImg":"http://sell.file.com/product/7ad86273-07c5-44ea-ae4e-7c4797ef9993.png","sellPrice":155}]"__proto__: Object
    private String id;
    private String name;
    private String logoImg;
    private Integer num;
    private String sellPrice;
}
