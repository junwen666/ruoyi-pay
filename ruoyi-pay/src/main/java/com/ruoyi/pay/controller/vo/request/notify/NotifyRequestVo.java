package com.ruoyi.pay.controller.vo.request.notify;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotifyRequestVo implements Serializable {

    static final long serialVersionUID = -1;

    private String paySn;

    private String channelUnique;

    private String channelCode;

}
