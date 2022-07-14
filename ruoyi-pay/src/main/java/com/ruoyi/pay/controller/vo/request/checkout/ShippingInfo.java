package com.ruoyi.pay.controller.vo.request.checkout;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShippingInfo implements Serializable {

    static final long serialVersionUID = -1;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 地址1
     */
    private String address1;

    /**
     * 地址2
     */
    private String address2;

    /**
     * 国家
     */
    private String country;

    /**
     * 城市
     */
    private String city;

    /**
     * 省、州
     */
    private String state;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 电话
     */
    private String phoneNumber;

}
