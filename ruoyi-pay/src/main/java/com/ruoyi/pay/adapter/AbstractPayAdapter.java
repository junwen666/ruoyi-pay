package com.ruoyi.pay.adapter;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.PayException;
import com.ruoyi.pay.controller.vo.request.checkout.CheckOutRequestVo;
import com.ruoyi.pay.controller.vo.request.notify.NotifyRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;
import com.ruoyi.pay.domain.PayChannel;
import com.ruoyi.pay.service.IPayChannelService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public abstract class AbstractPayAdapter {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${pay.return.url}")
    protected String PAY_RETURN_URL;

    @Autowired
    private IPayChannelService channelService;

    /**
     * 处理支付结算
     *
     * @param checkOutRequestVo
     * @return
     */
    public abstract CheckOutResponse doCheckout(CheckOutRequestVo checkOutRequestVo);

    /**
     * 处理支付通知
     *
     * @return
     */
    public abstract CheckOutResponse doNotify(NotifyRequestVo notifyRequestDto);

    protected <T> T getPayAccount(String channelCode, Class<T> tClass) {
        PayChannel channel = new PayChannel();
        channel.setChannelCode(channelCode);
        List<PayChannel> payChannels = channelService.selectPayChannelList(channel);
        if (CollectionUtils.isNotEmpty(payChannels)) {
            PayChannel payChannel = payChannels.get(0);
            return JSON.parseObject(payChannel.getChannelAuthInfo(), tClass);
        } else {
            throw new PayException("channelCode not found");
        }
    }

}
