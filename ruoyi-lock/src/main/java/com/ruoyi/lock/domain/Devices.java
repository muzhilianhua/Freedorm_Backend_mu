package com.ruoyi.lock.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备对象 devices
 * 
 * @author Maxing
 * @date 2025-01-04
 */
public class Devices extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */
    @Excel(name = "设备id")
    private String deviceId;

    /** mac地址 */
    @Excel(name = "mac地址")
    private String macAddress;

    /** 是否启用 */
    @Excel(name = "是否禁用")
    private Integer isEnabled;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }
    public void setMacAddress(String macAddress) 
    {
        this.macAddress = macAddress;
    }

    public String getMacAddress() 
    {
        return macAddress;
    }
    public void setIsEnabled(Integer isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public Integer getIsEnabled() 
    {
        return isEnabled;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("macAddress", getMacAddress())
            .append("isEnabled", getIsEnabled())
            .append("deptId", getDeptId())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
