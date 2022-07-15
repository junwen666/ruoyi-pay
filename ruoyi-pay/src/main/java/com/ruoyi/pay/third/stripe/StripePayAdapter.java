package com.ruoyi.pay.third.stripe;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.ChannelCodeEnum;
import com.ruoyi.common.enums.ThirdPayStatusEnum;
import com.ruoyi.common.exception.PayException;
import com.ruoyi.common.okhttp.HttpDto;
import com.ruoyi.common.okhttp.OkHttpUtils;
import com.ruoyi.common.utils.AmoutUtils;
import com.ruoyi.common.utils.HttpMediaType;
import com.ruoyi.common.utils.ParamUtils;
import com.ruoyi.pay.adapter.AbstractPayAdapter;
import com.ruoyi.pay.adapter.Adapter;
import com.ruoyi.pay.controller.vo.request.checkout.CardInfo;
import com.ruoyi.pay.controller.vo.request.checkout.CheckOutRequestVo;
import com.ruoyi.pay.controller.vo.request.checkout.ShippingInfo;
import com.ruoyi.pay.controller.vo.request.notify.NotifyRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;
import com.ruoyi.pay.third.stripe.dto.StripeAccountDto;
import com.ruoyi.pay.third.stripe.dto.StripeOrderRequest;
import com.ruoyi.pay.third.stripe.dto.StripeStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Adapter(ChannelCodeEnum.STRIPE)
public class StripePayAdapter extends AbstractPayAdapter {

    private static final String STRIPE_URL = "https://api.stripe.com";

    private static final String INTENTS_URL = "/v1/payment_intents";

    private static final String REFUND_URL = "/v1/refunds";

    @Override
    public CheckOutResponse doCheckout(CheckOutRequestVo checkOutRequestVo) {
        StripeAccountDto stripeAccountDto = getPayAccount(checkOutRequestVo.getChannelCode(), StripeAccountDto.class);

        StripeOrderRequest stripeOrderRequest = buildStripeOrderRequest(checkOutRequestVo);

        HttpDto dto = new HttpDto();
        Map<String, String> header = new HashMap<>();
        header.put("Idempotency-Key", checkOutRequestVo.getPaySn());
        dto.setUrl(STRIPE_URL + INTENTS_URL);
        dto.setHttpMethod(HttpMethod.POST);
        dto.setData(ParamUtils.formString(stripeOrderRequest));
        dto.setUsername(stripeAccountDto.getPrivateKey());
        dto.setHeaders(header);
        dto.setMediaType(HttpMediaType.FORM);

        JSONObject result = OkHttpUtils.doRequest(dto);

        return processingPayResponse(result, checkOutRequestVo.getPaySn());
    }

    @Override
    public CheckOutResponse doNotify(NotifyRequestVo notifyRequestDto) {
        StripeAccountDto stripeAccountDto = getPayAccount(notifyRequestDto.getChannelCode(), StripeAccountDto.class);

        HttpDto dto = new HttpDto();
        dto.setUrl(STRIPE_URL.concat("/").concat(notifyRequestDto.getChannelUnique()));
        dto.setHttpMethod(HttpMethod.GET);
        dto.setUsername(stripeAccountDto.getPrivateKey());

        JSONObject result = OkHttpUtils.doRequest(dto);
        return processingPayResponse(result, notifyRequestDto.getPaySn());
    }

    private CheckOutResponse processingPayResponse(JSONObject response, String paySn) {
        if (Objects.isNull(response) || response.isEmpty()) {
            logger.error("stripe payment response is empty, paySn=[{}]", paySn);
            throw new PayException("stripe payment response is empty");
        }
        CheckOutResponse result = new CheckOutResponse();
        result.setPaySn(paySn);
        result.setChannelUnique(response.getString("id"));

        String status = response.getString("status");
        if (StringUtils.equals(StripeStatusEnum.SUCCEEDED.getCode(), status)) {
            result.setThirdPayStatus(ThirdPayStatusEnum.SUCCESS.getCode());

        } else if (StringUtils.equals(StripeStatusEnum.REQUIRES_ACTION.getCode(), status)) {
            result.setThirdPayStatus(ThirdPayStatusEnum.REDIRECT.getCode());
            result.setRedirectUrl(resolveRedirectUrl(response));
        } else {
            result.setThirdPayStatus(ThirdPayStatusEnum.FAIL.getCode());
            buildErrorInfo(result, response);
        }
        return result;
    }

    private void buildErrorInfo(CheckOutResponse result, JSONObject response) {
        if (response.containsKey("error")) {
            result.setErrorCode(response.getJSONObject("error").getString("code"));
            result.setErrorMessage(response.getJSONObject("error").getString("message"));
        } else if (response.containsKey("last_payment_error")) {
            result.setErrorCode(response.getJSONObject("last_payment_error").getString("code"));
            result.setErrorMessage(response.getJSONObject("last_payment_error").getString("message"));
        }
    }

    private String resolveRedirectUrl(JSONObject response) {
        try {
            String url = response.getJSONObject("next_action").getJSONObject("redirect_to_url").getString("url");
            return url;
        } catch (Exception e) {
            logger.error("stripe payment resolveRedirectUrl error");
            throw new PayException("stripe payment resolveRedirectUrl error");
        }
    }

    private StripeOrderRequest buildStripeOrderRequest(CheckOutRequestVo checkOutRequestVo) {
        StripeOrderRequest request = new StripeOrderRequest();

        request.setAmount(AmoutUtils.toCent(checkOutRequestVo.getAmount()));
        request.setCurrency(checkOutRequestVo.getCurrency());
        request.setPaymentMethodDataType("card");

        CardInfo card = checkOutRequestVo.getCardInfo();
        request.setPaymentMethodDataCardExpMonth(card.getMonth());
        request.setPaymentMethodDataCardExpYear(card.getYear());
        request.setPaymentMethodDataCardNumber(card.getCardNo());
        request.setPaymentMethodDataCardCvc(card.getCvv());

        request.setPaymentMethodDataIp(checkOutRequestVo.getIp());
        //request.setPaymentMethodDataUserAgent("");
        //request.setPaymentMethodDataReferrer(preUrl + );
        request.setReturnUrl(PAY_RETURN_URL + "/pay/notify/stripe/return?paySn=" + checkOutRequestVo.getPaySn());
        request.setMetadataPaysn(checkOutRequestVo.getPaySn());
        request.setConfirm("true");
        request.setConfirmationMethod("automatic");

        ShippingInfo shipping = checkOutRequestVo.getShippingInfo();
        request.setShippingAddressCountry(shipping.getCountry());
        request.setShippingAddressState(shipping.getState());
        request.setShippingAddressCity(shipping.getCity());
        request.setShippingAddressLine1(shipping.getAddress1());
        request.setShippingAddressLine2(shipping.getAddress2());
        request.setShippingAddressPostalCode(shipping.getPostalCode());
        request.setShippingName(StringUtils.join(shipping.getFirstName(), shipping.getLastName()));

        return request;
    }

}
