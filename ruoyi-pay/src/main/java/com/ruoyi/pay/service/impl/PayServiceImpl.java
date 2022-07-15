package com.ruoyi.pay.service.impl;

import com.ruoyi.common.exception.PayException;
import com.ruoyi.pay.adapter.AbstractPayAdapter;
import com.ruoyi.pay.adapter.Adapter;
import com.ruoyi.pay.controller.vo.request.checkout.CheckOutRequestVo;
import com.ruoyi.pay.controller.vo.request.notify.NotifyRequestVo;
import com.ruoyi.pay.controller.vo.response.CheckOutResponse;
import com.ruoyi.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {

    private final Map<String, AbstractPayAdapter> payAdapterMap;

    @Autowired
    public PayServiceImpl(List<AbstractPayAdapter> payAdapterList) {
        this.payAdapterMap = Collections.unmodifiableMap(buildPayStrategies(payAdapterList));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public CheckOutResponse doCheckOut(CheckOutRequestVo checkOutRequestVo) {
        AbstractPayAdapter payAdapter = getPayAdapter(checkOutRequestVo.getChannelCode());
        return payAdapter.doCheckout(checkOutRequestVo);
    }

    @Override
    public CheckOutResponse doNotify(NotifyRequestVo notifyRequestVo) {
        AbstractPayAdapter payAdapter = getPayAdapter(notifyRequestVo.getChannelCode());
        return payAdapter.doNotify(notifyRequestVo);
    }

    AbstractPayAdapter getPayAdapter(String channelCode) {
        AbstractPayAdapter payAdapter = payAdapterMap.get(channelCode);
        if (Objects.isNull(payAdapter)) {
            throw new PayException("channelCode not found");
        }
        return payAdapter;
    }

    private Map<String, AbstractPayAdapter> buildPayStrategies(List<AbstractPayAdapter> payAdapterList) {
        return payAdapterList.stream()
                .filter(payAdapter -> payAdapter.getClass().isAnnotationPresent(Adapter.class))
                .collect(Collectors.toMap(o -> o.getClass().getAnnotation(Adapter.class).value().getCode(), o -> o));
    }

}
