package com.ruoyi.pay.third.stripe.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StripeAccountDto implements Serializable {

    static final long serialVersionUID = -1;

    private String privateKey;

    private String publicKey;

}
