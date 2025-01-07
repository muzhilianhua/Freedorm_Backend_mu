package com.ruoyi.lock.service;

import java.util.List;
import com.ruoyi.lock.domain.Devices;
import com.ruoyi.lock.dto.BindDeviceRequest;

/**
 * 设备Service接口
 * 
 * @author Maxing
 * @date 2025-01-04
 */
public interface IDevicesService 
{
    /**
     * 查询设备
     * 
     * @param deviceId 设备主键
     * @return 设备
     */
    public Devices selectDevicesByDeviceId(String deviceId);

    /**
     * 查询设备列表
     * 
     * @param devices 设备
     * @return 设备集合
     */
    public List<Devices> selectDevicesList(Devices devices);

    /**
     * 新增设备
     * 
     * @param devices 设备
     * @return 结果
     */
    public int insertDevices(Devices devices);

    /**
     * 修改设备
     * 
     * @param devices 设备
     * @return 结果
     */
    public int updateDevices(Devices devices);

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteDevicesByDeviceIds(String[] deviceIds);

    /**
     * 删除设备信息
     * 
     * @param deviceId 设备主键
     * @return 结果
     */
    public int deleteDevicesByDeviceId(String deviceId);

    Boolean deviceIsOnline(String deviceId);

    Boolean bindDevice(BindDeviceRequest request);

    boolean unbindDevice();
}
