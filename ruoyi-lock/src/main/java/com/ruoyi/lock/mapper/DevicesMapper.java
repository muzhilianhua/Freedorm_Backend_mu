package com.ruoyi.lock.mapper;

import java.util.List;
import com.ruoyi.lock.domain.Devices;

/**
 * 设备Mapper接口
 * 
 * @author Maxing
 * @date 2025-01-04
 */
public interface DevicesMapper 
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
     * 删除设备
     * 
     * @param deviceId 设备主键
     * @return 结果
     */
    public int deleteDevicesByDeviceId(String deviceId);

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDevicesByDeviceIds(String[] deviceIds);
}
