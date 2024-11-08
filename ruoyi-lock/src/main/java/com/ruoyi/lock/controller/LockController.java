package com.ruoyi.lock.controller;

import com.ruoyi.lock.service.MqttGateway;
import com.ruoyi.lock.domain.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lock")
public class LockController {

    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(LockController.class);

    @PostMapping("/doorOpenOnce")
    public String doorOpenOnce(@RequestParam String deviceId, @RequestParam int duration) {
        Map<String, Object> data = new HashMap<>();
        data.put("duration", duration);

        MqttMessage<Map<String, Object>> message = new MqttMessage<>();
        message.setOperate("door_open_once");
        message.setTimestamp(System.currentTimeMillis() / 1000);
        message.setData(data);

        String topic = "/" + deviceId + "/server2client";
        mqttGateway.sendToMqtt(topic, message);
        logger.info("Sending message to topic {}: {}", topic, message);
        return "Command sent";
    }

    // 添加其他操作类型的接口，例如 doorOpenTimer、doorLock 等
}
