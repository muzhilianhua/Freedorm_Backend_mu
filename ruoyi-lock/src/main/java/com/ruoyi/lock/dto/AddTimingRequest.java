package com.ruoyi.lock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


public class AddTimingRequest {

    @NotEmpty(message = "daysOfWeek不能为空")
    private List<Integer> daysOfWeek; // 1=星期一，7=星期日

    @NotNull(message = "startTime不能为空")
    private String startTime;

    @NotNull(message = "endTime不能为空")
    private String endTime;

    // Getters and Setters

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
