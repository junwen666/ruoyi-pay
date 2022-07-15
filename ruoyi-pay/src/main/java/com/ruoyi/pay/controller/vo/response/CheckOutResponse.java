package com.ruoyi.pay.controller.vo.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CheckOutResponse implements Serializable {

    /**
     * 支付唯一编号
     */
    private String paySn;

    /**
     * 渠道方唯一编码
     */
    private String channelUnique;

    /**
     * 支付状态
     */
    private int thirdPayStatus;

    /**
     * 跳转页面
     */
    private String redirectUrl;

    /**
     * 第三方错误码
     */
    private String errorCode;

    /**
     *  第三方错误描述
     */
    private String errorMessage;

}
