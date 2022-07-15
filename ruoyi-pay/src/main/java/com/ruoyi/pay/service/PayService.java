package com.ruoyi.pay.service;

import com.ruoyi.pay.controller.vo.request.checkout.CheckOutRequestVo;
import com.ruoyi.pay.controller.vo.request.notify.NotifyRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;

public interface PayService {

    CheckOutResponse doCheckOut(CheckOutRequestVo checkOutRequestVo);

    CheckOutResponse doNotify(NotifyRequestVo notifyRequestVo);

}
