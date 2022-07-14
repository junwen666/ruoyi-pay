package com.ruoyi.pay.controller.vo.request.checkout;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemInfo implements Serializable {

    static final long serialVersionUID = -1;

    private String sku;

    private String name;

    private BigDecimal price;

    private Integer qty;

}
