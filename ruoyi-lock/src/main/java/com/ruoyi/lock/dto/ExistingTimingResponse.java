package com.ruoyi.lock.dto;

import java.util.List;

public class ExistingTimingResponse {
    private String deviceId;
    private List<Integer> daysOfWeek; // 1=星期一，7=星期日
    private String startTime; // "HH:mm:ss"
    private String endTime;

    // Constructors

    public ExistingTimingResponse() {
    }

    public ExistingTimingResponse(String deviceId, List<Integer> daysOfWeek, String startTime, String endTime) {
        this.deviceId = deviceId;
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
