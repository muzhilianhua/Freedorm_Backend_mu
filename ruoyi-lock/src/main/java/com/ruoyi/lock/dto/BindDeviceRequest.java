package com.ruoyi.lock.dto;

import javax.validation.constraints.NotBlank;

public class BindDeviceRequest {
    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    @NotBlank(message = "deptName不能为空")
    private String deptName;

    // Getters and Setters
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
