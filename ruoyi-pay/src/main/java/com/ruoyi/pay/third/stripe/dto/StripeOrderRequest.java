package com.ruoyi.pay.third.stripe.dto;

import com.ruoyi.common.utils.annotation.FormKey;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StripeOrderRequest implements Serializable {

    @FormKey(ignore = true)
    static final long serialVersionUID = -1;

    private Integer amount;

    private String currency;

    @FormKey("payment_method_data[type]")
    private String paymentMethodDataType;

    @FormKey("payment_method_data[card][exp_month]")
    private String paymentMethodDataCardExpMonth;

    @FormKey("payment_method_data[card][exp_year]")
    private String paymentMethodDataCardExpYear;

    @FormKey("payment_method_data[card][number]")
    private String paymentMethodDataCardNumber;

    @FormKey("payment_method_data[card][cvc]")
    private String paymentMethodDataCardCvc;

    @FormKey("payment_method_data[ip]")
    private String paymentMethodDataIp;

    /*@FormKey("payment_method_data[user_agent]")
    private String paymentMethodDataUserAgent;

    @FormKey("payment_method_data[referrer]")
    private String paymentMethodDataReferrer;*/

    @FormKey("return_url")
    private String returnUrl;

    @FormKey("metadata[paySn]")
    private String metadataPaysn;

    private String confirm;

    @FormKey("confirmation_method")
    private String confirmationMethod;

    @FormKey("shipping[address][country]")
    private String shippingAddressCountry;

    @FormKey("shipping[address][state]")
    private String shippingAddressState;

    @FormKey("shipping[address][city]")
    private String shippingAddressCity;

    @FormKey("shipping[address][line1]")
    private String shippingAddressLine1;

    @FormKey("shipping[address][line2]")
    private String shippingAddressLine2;

    @FormKey("shipping[address][postal_code]")
    private String shippingAddressPostalCode;

    @FormKey("shipping[name]")
    private String shippingName;
}
