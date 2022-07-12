package com.ruoyi.pay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付渠道对象 pay_channel
 * 
 * @author ruoyi
 * @date 2022-07-12
 */
public class PayChannel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long channelId;

    /** 渠道编码 */
    @Excel(name = "渠道编码")
    private String channelCode;

    /** 渠道名称 */
    @Excel(name = "渠道名称")
    private String channelName;

    /** 渠道展示图片 */
    @Excel(name = "渠道展示图片")
    private String channelImg;

    /** 渠道认证信息(JSON) */
    @Excel(name = "渠道认证信息(JSON)")
    private String channelAuthInfo;

    /** 是否信用卡支付 */
    @Excel(name = "是否信用卡支付")
    private Integer isCredit;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 部门状态（0正常 1停用） */
    @Excel(name = "部门状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setChannelId(Long channelId) 
    {
        this.channelId = channelId;
    }

    public Long getChannelId() 
    {
        return channelId;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setChannelImg(String channelImg) 
    {
        this.channelImg = channelImg;
    }

    public String getChannelImg() 
    {
        return channelImg;
    }
    public void setChannelAuthInfo(String channelAuthInfo) 
    {
        this.channelAuthInfo = channelAuthInfo;
    }

    public String getChannelAuthInfo() 
    {
        return channelAuthInfo;
    }
    public void setIsCredit(Integer isCredit) 
    {
        this.isCredit = isCredit;
    }

    public Integer getIsCredit() 
    {
        return isCredit;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("channelId", getChannelId())
            .append("channelCode", getChannelCode())
            .append("channelName", getChannelName())
            .append("channelImg", getChannelImg())
            .append("channelAuthInfo", getChannelAuthInfo())
            .append("isCredit", getIsCredit())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
