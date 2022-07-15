package com.ruoyi.common.enums;

public enum ChannelCodeEnum {

    PAYPAL("PayPal", "PayPal"),

    KLARNA("Klarna", "Klarna"),

    STRIPE("Stripe", "Stripe"),

    ;


    ChannelCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
