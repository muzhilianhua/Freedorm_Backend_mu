package com.ruoyi.lock.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lock.mapper.DevicesMapper;
import com.ruoyi.lock.domain.Devices;
import com.ruoyi.lock.service.IDevicesService;

/**
 * 设备Service业务层处理
 * 
 * @author Maxing
 * @date 2025-01-04
 */
@Service
public class DevicesServiceImpl implements IDevicesService 
{
    @Autowired
    private DevicesMapper devicesMapper;

    /**
     * 查询设备
     * 
     * @param deviceId 设备主键
     * @return 设备
     */
    @Override
    public Devices selectDevicesByDeviceId(String deviceId)
    {
        return devicesMapper.selectDevicesByDeviceId(deviceId);
    }

    /**
     * 查询设备列表
     * 
     * @param devices 设备
     * @return 设备
     */
    @Override
    public List<Devices> selectDevicesList(Devices devices)
    {
        return devicesMapper.selectDevicesList(devices);
    }

    /**
     * 新增设备
     * 
     * @param devices 设备
     * @return 结果
     */
    @Override
    public int insertDevices(Devices devices)
    {
        return devicesMapper.insertDevices(devices);
    }

    /**
     * 修改设备
     * 
     * @param devices 设备
     * @return 结果
     */
    @Override
    public int updateDevices(Devices devices)
    {
        return devicesMapper.updateDevices(devices);
    }

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteDevicesByDeviceIds(String[] deviceIds)
    {
        return devicesMapper.deleteDevicesByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息
     * 
     * @param deviceId 设备主键
     * @return 结果
     */
    @Override
    public int deleteDevicesByDeviceId(String deviceId)
    {
        return devicesMapper.deleteDevicesByDeviceId(deviceId);
    }
}
