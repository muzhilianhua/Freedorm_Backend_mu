package com.ruoyi.lock.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lock.dto.AddTimingRequest;
import com.ruoyi.lock.dto.DeleteTimingRequest;
import com.ruoyi.lock.service.ILockService;
import com.ruoyi.lock.service.MqttGateway;
import com.ruoyi.lock.domain.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lock")
public class LockController {

    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private ILockService lockService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(LockController.class);

    @PostMapping("/doorOpenOnce")
    public AjaxResult doorOpenOnce(@RequestBody Map<String, Object> requestBody) {
        String deviceId = (String) requestBody.get("deviceId");
        int duration = (int) requestBody.get("duration");
        Map<String, Object> data = new HashMap<>();
        data.put("duration", duration);

        MqttMessage<Map<String, Object>> message = new MqttMessage<>();
        message.setOperate("door_open_once");
        message.setTimestamp(System.currentTimeMillis() / 1000);
        message.setData(data);

        String topic = "/" + deviceId + "/server2client";
        mqttGateway.sendToMqtt(topic, message);
        logger.info("Sending message to topic {}: {}", topic, message);
        return AjaxResult.success();
    }
    /**
     * 新增定时开门时间段
     */
    @PostMapping("/timing/add")
    public AjaxResult addTiming(@Valid @RequestBody AddTimingRequest request) {
        try {
            lockService.addTiming(request);
            logger.info("Added timing for deviceId: {}", request.getDeviceId());
            return AjaxResult.success("定时开门时间段添加成功");
        } catch (Exception e) {
            logger.error("Failed to add timing for deviceId: {}", request.getDeviceId(), e);
            return AjaxResult.error("定时开门时间段添加失败: " + e.getMessage());
        }
    }

    /**
     * 删除定时开门时间段
     */
    @DeleteMapping("/timing/delete")
    public AjaxResult deleteTiming(@Valid @RequestBody DeleteTimingRequest request) {
        try {
            lockService.deleteTiming(request);
            logger.info("Deleted timing for deviceId: {}", request.getDeviceId());
            return AjaxResult.success("定时开门时间段删除成功");
        } catch (Exception e) {
            logger.error("Failed to delete timing for deviceId: {}", request.getDeviceId(), e);
            return AjaxResult.error("定时开门时间段删除失败: " + e.getMessage());
        }
    }
    // 添加其他操作类型的接口，例如 doorOpenTimer、doorLock 等
}
