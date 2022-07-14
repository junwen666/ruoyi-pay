package com.ruoyi.pay.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.controller.vo.request.checkout.CheckOutRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;
import com.ruoyi.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/pay/checkOut")
public class CheckOutController extends BaseController {

    private final String prefix = "pay/checkOut";

    @Autowired
    private PayService payService;

    @PostMapping("checkOut")
    @ResponseBody
    public AjaxResult checkOut(@RequestBody CheckOutRequestVo checkOutRequestVo) {
        checkOutRequestVo.setPaySn(UUID.randomUUID().toString().replaceAll("-", ""));
        CheckOutResponse checkOutResponse = payService.doCheckOut(checkOutRequestVo);
        return AjaxResult.success(checkOutResponse);
    }

    /**
     * checkOut参数
     * cardNo：
     *      success:    4242424242424242
     *      3ds:        4000002760003184
     *      fail：      4100000000000019
     *
     *
     * {
     *     "userId": "10001",
     *     "amount": "99.99",
     *     "currency": "USD",
     *     "channelCode": "Stripe",
     *     "orderNo": "O2022041816516565",
     *     "ip": "240.21.224.144",
     *     "returnUrl": "http://baidu.com",
     *     "errorUrl": "http://bing.com",
     *     "cardInfo": {
     *         "cardNo": "4242424242424242",
     *         "month": "10",
     *         "year": "2022",
     *         "cvv": "123",
     *         "cardType": "visa",
     *         "cardHolder": "minjunwen"
     *     },
     *     "shippingInfo": {
     *         "firstName": "junwen",
     *         "lastName": "min",
     *         "address1": "guangdong shenzhen",
     *         "address2": "nanshan 2202",
     *         "country": "china",
     *         "city": "shenzhen",
     *         "state": "guangdong",
     *         "postalCode": "710043",
     *         "phoneNumber": "18792982047"
     *     },
     *     "orderItemInfoList": [
     *         {
     *             "name": "cat",
     *             "sku": "cat001",
     *             "qty": "1",
     *             "price": "9.00"
     *         },
     *         {
     *             "price": "9.99",
     *             "name": "dog",
     *             "qty": "2",
     *             "sku": "dog001"
     *         }
     *     ]
     * }
     */

}
