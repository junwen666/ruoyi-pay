package com.ruoyi.pay.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.ChannelCodeEnum;
import com.ruoyi.common.enums.ThirdPayStatusEnum;
import com.ruoyi.common.utils.RequestUtils;
import com.ruoyi.pay.controller.vo.request.notify.NotifyRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;
import com.ruoyi.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pay/notify")
public class NotifyController extends BaseController {

    private final String prefix = "pay/notify";

    @Autowired
    private PayService payService;

    @RequestMapping("/stripe")
    @ResponseBody
    public String stripeNotify() {

        String content = RequestUtils.getRequestContent(request);
        //..
        payService.doNotify(null);

        return "OK";
    }

    /**
     * notify地址在官方配置中，无权限修改，此处使用return作为notify用，实际return仅用于页面跳转
     *
     * @return
     */
    @RequestMapping("/stripe/return")
    public String stripeReturn(String payment_intent, String paySn) {

        NotifyRequestVo notifyRequestVo = new NotifyRequestVo();
        notifyRequestVo.setPaySn(paySn);
        notifyRequestVo.setChannelUnique(payment_intent);
        notifyRequestVo.setChannelCode(ChannelCodeEnum.STRIPE.getCode());
        CheckOutResponse checkOutResponse = payService.doNotify(notifyRequestVo);

        if(checkOutResponse.getThirdPayStatus() == ThirdPayStatusEnum.SUCCESS.getCode()){
            return redirect(prefix + "/success.html");
        }

        return redirect(prefix + "/error.html");
    }

}
