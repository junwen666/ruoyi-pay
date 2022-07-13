package com.ruoyi.pay.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.pay.domain.PayChannel;
import com.ruoyi.pay.mapper.PayChannelMapper;
import com.ruoyi.pay.service.IPayChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付渠道Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-12
 */
@Service
public class PayChannelServiceImpl implements IPayChannelService {
    @Autowired
    private PayChannelMapper payChannelMapper;

    /**
     * 查询支付渠道
     *
     * @param channelId 支付渠道主键
     * @return 支付渠道
     */
    @Override
    public PayChannel selectPayChannelByChannelId(Long channelId) {
        return payChannelMapper.selectPayChannelByChannelId(channelId);
    }

    /**
     * 查询支付渠道列表
     *
     * @param payChannel 支付渠道
     * @return 支付渠道
     */
    @Override
    public List<PayChannel> selectPayChannelList(PayChannel payChannel) {
        return payChannelMapper.selectPayChannelList(payChannel);
    }

    /**
     * 新增支付渠道
     *
     * @param payChannel 支付渠道
     * @return 结果
     */
    @Override
    public int insertPayChannel(PayChannel payChannel) {
        payChannel.setCreateTime(DateUtils.getNowDate());
        return payChannelMapper.insertPayChannel(payChannel);
    }

    /**
     * 修改支付渠道
     *
     * @param payChannel 支付渠道
     * @return 结果
     */
    @Override
    public int updatePayChannel(PayChannel payChannel) {
        payChannel.setUpdateTime(DateUtils.getNowDate());
        return payChannelMapper.updatePayChannel(payChannel);
    }

    /**
     * 批量删除支付渠道
     *
     * @param channelIds 需要删除的支付渠道主键
     * @return 结果
     */
    @Override
    public int deletePayChannelByChannelIds(String channelIds) {
        return payChannelMapper.deletePayChannelByChannelIds(Convert.toStrArray(channelIds));
    }

    /**
     * 删除支付渠道信息
     *
     * @param channelId 支付渠道主键
     * @return 结果
     */
    @Override
    public int deletePayChannelByChannelId(Long channelId) {
        return payChannelMapper.deletePayChannelByChannelId(channelId);
    }
}
