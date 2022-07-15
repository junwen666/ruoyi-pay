package com.ruoyi.common.enums;

public enum PayStatusEnum {
    PAY_NOPAY(0, "未支付 "),

    PAY_PENDING(1, "处理中"),

    PAY_COMPLETED(2, "已支付"),

    PAY_FAILD(3, "支付失败"),

    REFUND_ING(4, "退款中"),

    REFUND_COMPLETED(5, "全部退款成功"),

    REFUND_PARTIAL(6, "部分退款成功"),

    REFUND_FAILD(7, "退款失败"),
    ;

    private int code;
    private String name;

    PayStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
