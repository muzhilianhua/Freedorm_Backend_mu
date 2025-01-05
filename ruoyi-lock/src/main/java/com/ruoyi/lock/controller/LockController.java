package com.ruoyi.lock.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.OverlappingTimingException;
import com.ruoyi.lock.domain.FreedormLockSchedule;
import com.ruoyi.lock.dto.AddTimingRequest;
import com.ruoyi.lock.dto.DeleteTimingRequest;
import com.ruoyi.lock.dto.ExistingTimingResponse;
import com.ruoyi.lock.mapper.FreedormLockSchedulesMapper;
import com.ruoyi.lock.service.ILockService;
import com.ruoyi.lock.service.MqttGateway;
import com.ruoyi.lock.domain.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruoyi.lock.service.impl.LockServiceImpl.isOverlapping;

@RestController
@RestControllerAdvice
@RequestMapping("/api/lock")
public class LockController {

    private static final Logger logger = LoggerFactory.getLogger(LockController.class);

    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private ILockService lockService;

    @Autowired
    private FreedormLockSchedulesMapper schedulesMapper;

    @PostMapping("/doorOpenOnce")
    @Log(title = "门锁操作", businessType = BusinessType.OPEN)
    public AjaxResult doorOpenOnce(@RequestBody Map<String, Object> requestBody){
        if (!requestBody.containsKey("deviceId") || !requestBody.containsKey("duration")) {
            return AjaxResult.error("请求参数缺失");
        }
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
        return AjaxResult.success("门锁操作成功");
    }

    /**
     * 新增定时开门时间段
     */
    @PostMapping("/timing/add")
    public AjaxResult addTiming(@Valid @RequestBody AddTimingRequest request){
        for (Integer day : request.getDaysOfWeek()) {
            if (day < 1 || day > 7) {
               return AjaxResult.error("dayOfWeek 必须在1到7之间");
            }
            // 检查是否有重叠的时间段
            List<FreedormLockSchedule> existingSchedules = schedulesMapper.findByDeviceIdAndDayOfWeek(request.getDeviceId(), day);
            for (FreedormLockSchedule existingSchedule : existingSchedules) {
                if (isOverlapping(existingSchedule.getStartTime(), existingSchedule.getEndTime(),
                        request.getStartTime().toString(), request.getEndTime().toString())) {
                    return AjaxResult.error("与现有时间段重叠，设备ID: " + request.getDeviceId() + ", 星期: " + day);
                }
            }
        }
        lockService.addTiming(request);
        logger.info("Added timing for deviceId: {}", request.getDeviceId());
        return AjaxResult.success("定时开门时间段添加成功");
    }

    /**
     * 删除定时开门时间段
     */
    @DeleteMapping("/timing/delete")
    public AjaxResult deleteTiming(@Valid @RequestBody DeleteTimingRequest request){
        lockService.deleteTiming(request);
        logger.info("Deleted timing for deviceId: {}", request.getDeviceId());
        return AjaxResult.success("定时开门时间段删除成功");
    }

    /**
     * 查询某个门锁已有的时间段
     * GET 请求，传递 deviceId 作为查询参数
     */
    @GetMapping("/timing/existing")
    public AjaxResult getExistingTimings(@RequestParam("deviceId") String deviceId){
        List<ExistingTimingResponse> existingTimings = lockService.getExistingTimings(deviceId);
        logger.info("Fetched existing timings for deviceId: {}", deviceId);
        return AjaxResult.success(existingTimings);
    }
    // 添加其他操作类型的接口，例如 doorOpenTimer、doorLock 等
}
