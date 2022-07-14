package com.ruoyi.pay.controller.vo.request.checkout;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CardInfo implements Serializable {

    static final long serialVersionUID = -1;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 月
     */
    private String month;

    /**
     * 年
     */
    private String year;

    /**
     * 安全码
     */
    private String cvv;

    /**
     * 卡种
     */
    private String cardType;

    /**
     * 持卡人
     **/
    private String cardHolder;

}
