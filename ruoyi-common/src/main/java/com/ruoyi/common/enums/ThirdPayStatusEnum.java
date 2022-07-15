package com.ruoyi.common.enums;

public enum ThirdPayStatusEnum {

    SUCCESS(0, "success", PayStatusEnum.PAY_COMPLETED),

    FAIL(1, "fail", PayStatusEnum.PAY_FAILD),

    REDIRECT(2, "redirect", PayStatusEnum.PAY_PENDING),
    ;

    private int code;
    private String name;
    private PayStatusEnum payStatus;

    ThirdPayStatusEnum(int code, String name, PayStatusEnum payStatus) {
        this.code = code;
        this.name = name;
        this.payStatus = payStatus;
    }

    public static ThirdPayStatusEnum of(int code) {
        for (ThirdPayStatusEnum thirdPayStatusEnum : values()) {
            if (thirdPayStatusEnum.code == code) {
                return thirdPayStatusEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }
}
