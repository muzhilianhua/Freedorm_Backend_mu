package com.ruoyi.lock.dto;

import javax.validation.constraints.NotBlank;

public class BindDeviceRequest {
    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    // Getters and Setters
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
