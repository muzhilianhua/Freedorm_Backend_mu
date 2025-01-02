package com.ruoyi.lock.service;

import com.ruoyi.lock.dto.AddTimingRequest;
import com.ruoyi.lock.dto.DeleteTimingRequest;

public interface ILockService {
    void addTiming(AddTimingRequest request) throws Exception;
    void deleteTiming(DeleteTimingRequest request) throws Exception;
}
