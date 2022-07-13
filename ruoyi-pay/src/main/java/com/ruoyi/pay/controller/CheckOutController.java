package com.ruoyi.pay.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.controller.vo.CheckOutRequestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pay/checkOut")
public class CheckOutController extends BaseController {

    private final String prefix = "pay/checkOut";

    @PostMapping("checkOut")
    @ResponseBody
    public AjaxResult checkOut(CheckOutRequestVo checkOutRequestVo){

        return AjaxResult.success();
    }

}
