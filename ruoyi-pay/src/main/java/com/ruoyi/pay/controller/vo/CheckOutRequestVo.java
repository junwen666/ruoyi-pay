package com.ruoyi.pay.controller.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CheckOutRequestVo implements Serializable {

    static final long serialVersionUID = -1;

    private Long userId;

    private BigDecimal amount;

    private String currency;

    private String channelCode;

    private String orderNo;

    private String ip;

    private String returnUrl;

    private String errorUrl;

    private List<OrderItemInfo> orderItemInfoList;

    private CardInfo cardInfo;

    private ShippingInfo shippingInfo;

}
