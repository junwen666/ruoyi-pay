package com.ruoyi.pay.service;

import com.ruoyi.pay.domain.PayChannel;

import java.util.List;

/**
 * 支付渠道Service接口
 *
 * @author ruoyi
 * @date 2022-07-12
 */
public interface IPayChannelService {
    /**
     * 查询支付渠道
     *
     * @param channelId 支付渠道主键
     * @return 支付渠道
     */
    PayChannel selectPayChannelByChannelId(Long channelId);

    /**
     * 查询支付渠道列表
     *
     * @param payChannel 支付渠道
     * @return 支付渠道集合
     */
    List<PayChannel> selectPayChannelList(PayChannel payChannel);

    /**
     * 新增支付渠道
     *
     * @param payChannel 支付渠道
     * @return 结果
     */
    int insertPayChannel(PayChannel payChannel);

    /**
     * 修改支付渠道
     *
     * @param payChannel 支付渠道
     * @return 结果
     */
    int updatePayChannel(PayChannel payChannel);

    /**
     * 批量删除支付渠道
     *
     * @param channelIds 需要删除的支付渠道主键集合
     * @return 结果
     */
    int deletePayChannelByChannelIds(String channelIds);

    /**
     * 删除支付渠道信息
     *
     * @param channelId 支付渠道主键
     * @return 结果
     */
    int deletePayChannelByChannelId(Long channelId);
}
