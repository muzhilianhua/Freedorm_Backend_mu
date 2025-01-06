package com.ruoyi.lock.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.lock.dto.BindDeviceRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lock.domain.Devices;
import com.ruoyi.lock.service.IDevicesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author Maxing
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/lock/devices")
public class DevicesController extends BaseController
{
    @Autowired
    private IDevicesService devicesService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:list')")
    @GetMapping("/list")
    public TableDataInfo list(Devices devices)
    {
        startPage();
        List<Devices> list = devicesService.selectDevicesList(devices);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Devices devices)
    {
        List<Devices> list = devicesService.selectDevicesList(devices);
        ExcelUtil<Devices> util = new ExcelUtil<Devices>(Devices.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") String deviceId)
    {
        return success(devicesService.selectDevicesByDeviceId(deviceId));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Devices devices)
    {
        return toAjax(devicesService.insertDevices(devices));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Devices devices)
    {
        return toAjax(devicesService.updateDevices(devices));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('lock:devices:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable String[] deviceIds)
    {
        return toAjax(devicesService.deleteDevicesByDeviceIds(deviceIds));
    }

    @GetMapping("/device_is_online/{deviceId}")
    public AjaxResult device_is_online(@PathVariable String deviceId)
    {
        return success(devicesService.device_is_online(deviceId));
    }

    @PostMapping("/bind_device")
    public AjaxResult bind_device(@RequestBody BindDeviceRequest request)
    {
        return toAjax(devicesService.bind_device(request));
    }
}
