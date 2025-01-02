package com.ruoyi.lock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public class DeleteTimingRequest {

    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    @NotEmpty(message = "daysOfWeek不能为空")
    private List<Integer> daysOfWeek; // 1=星期一，7=星期日

    @NotNull(message = "startTime不能为空")
    private LocalTime startTime;

    @NotNull(message = "endTime不能为空")
    private LocalTime endTime;

    // Getters and Setters

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
