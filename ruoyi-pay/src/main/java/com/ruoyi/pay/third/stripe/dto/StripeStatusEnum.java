package com.ruoyi.pay.third.stripe.dto;

public enum StripeStatusEnum {
    REQUIRES_ACTION("requires_action"),

    SUCCEEDED("succeeded"),
    ;

    private String code;

    StripeStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
