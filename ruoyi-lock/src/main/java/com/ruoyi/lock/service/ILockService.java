package com.ruoyi.lock.service;

import com.ruoyi.lock.dto.AddTimingRequest;
import com.ruoyi.lock.dto.DeleteTimingRequest;
import com.ruoyi.lock.dto.ExistingTimingResponse;

import java.util.List;

public interface ILockService {
    void addTiming(AddTimingRequest request);
    void deleteTiming(DeleteTimingRequest request);
    List<ExistingTimingResponse> getExistingTimings(String deviceId);
}
