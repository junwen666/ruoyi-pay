package com.ruoyi.pay.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pay.domain.PayChannel;
import com.ruoyi.pay.service.IPayChannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 支付渠道Controller
 *
 * @author ruoyi
 * @date 2022-07-12
 */
@Controller
@RequestMapping("/pay/channel")
public class PayChannelController extends BaseController {
    private final String prefix = "pay/channel";

    @Autowired
    private IPayChannelService payChannelService;

    @RequiresPermissions("pay:channel:view")
    @GetMapping()
    public String channel() {
        return prefix + "/channel";
    }

    /**
     * 查询支付渠道列表
     */
    @RequiresPermissions("pay:channel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PayChannel payChannel) {
        startPage();
        List<PayChannel> list = payChannelService.selectPayChannelList(payChannel);
        return getDataTable(list);
    }

    /**
     * 导出支付渠道列表
     */
    @RequiresPermissions("pay:channel:export")
    @Log(title = "支付渠道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayChannel payChannel) {
        List<PayChannel> list = payChannelService.selectPayChannelList(payChannel);
        ExcelUtil<PayChannel> util = new ExcelUtil<PayChannel>(PayChannel.class);
        return util.exportExcel(list, "支付渠道数据");
    }

    /**
     * 新增支付渠道
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存支付渠道
     */
    @RequiresPermissions("pay:channel:add")
    @Log(title = "支付渠道", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PayChannel payChannel) {
        return toAjax(payChannelService.insertPayChannel(payChannel));
    }

    /**
     * 修改支付渠道
     */
    @RequiresPermissions("pay:channel:edit")
    @GetMapping("/edit/{channelId}")
    public String edit(@PathVariable("channelId") Long channelId, ModelMap mmap) {
        PayChannel payChannel = payChannelService.selectPayChannelByChannelId(channelId);
        mmap.put("payChannel", payChannel);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付渠道
     */
    @RequiresPermissions("pay:channel:edit")
    @Log(title = "支付渠道", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PayChannel payChannel) {
        return toAjax(payChannelService.updatePayChannel(payChannel));
    }

    /**
     * 删除支付渠道
     */
    @RequiresPermissions("pay:channel:remove")
    @Log(title = "支付渠道", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(payChannelService.deletePayChannelByChannelIds(ids));
    }
}
