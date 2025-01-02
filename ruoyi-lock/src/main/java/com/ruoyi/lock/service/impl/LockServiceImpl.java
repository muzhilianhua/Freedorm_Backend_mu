package com.ruoyi.lock.service.impl;

import com.ruoyi.lock.domain.FreedormLockSchedule;
import com.ruoyi.lock.dto.AddTimingRequest;
import com.ruoyi.lock.dto.DeleteTimingRequest;
import com.ruoyi.lock.mapper.FreedormLockSchedulesMapper;
import com.ruoyi.lock.service.ILockService;
import com.ruoyi.lock.service.MqttGateway;
import com.ruoyi.lock.domain.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class LockServiceImpl implements ILockService {

    @Autowired
    private FreedormLockSchedulesMapper schedulesMapper;

    @Autowired
    private MqttGateway mqttGateway;

    @Override
    @Transactional
    public void addTiming(AddTimingRequest request) throws Exception {
        // 创建新的时间表记录
        FreedormLockSchedule schedule = new FreedormLockSchedule();
        schedule.setDeviceId(request.getDeviceId());
        schedule.setStartTime(request.getStartTime().toString());
        schedule.setEndTime(request.getEndTime().toString());
        schedule.setDayOfWeek(getCurrentDayOfWeek()); // 根据需要设置
        schedulesMapper.insertFreedormLockSchedule(schedule);

        // 发送 MQTT 消息给设备，通知添加定时开门时间段
        Map<String, Object> data = new HashMap<>();
        data.put("action", "add_timing");
        data.put("start_time", schedule.getStartTime());
        data.put("end_time", schedule.getEndTime());

        MqttMessage<Map<String, Object>> message = new MqttMessage<>();
        message.setOperate("timing_update");
        message.setTimestamp(System.currentTimeMillis() / 1000);
        message.setData(data);

        String topic = "/" + request.getDeviceId() + "/server2client";
        mqttGateway.sendToMqtt(topic, message);
    }

    @Override
    @Transactional
    public void deleteTiming(DeleteTimingRequest request) throws Exception {
        // 查找并删除对应的时间表记录
        FreedormLockSchedule schedule = new FreedormLockSchedule();
        schedule.setDeviceId(request.getDeviceId());
        schedule.setStartTime(request.getStartTime().toString());
        schedule.setEndTime(request.getEndTime().toString());

        int deletedRows = schedulesMapper.deleteFreedormLockSchedule(schedule);
        if (deletedRows == 0) {
            throw new Exception("未找到对应的时间段记录");
        }

        // 发送 MQTT 消息给设备，通知删除定时开门时间段
        Map<String, Object> data = new HashMap<>();
        data.put("action", "delete_timing");
        data.put("start_time", schedule.getStartTime());
        data.put("end_time", schedule.getEndTime());

        MqttMessage<Map<String, Object>> message = new MqttMessage<>();
        message.setOperate("timing_update");
        message.setTimestamp(System.currentTimeMillis() / 1000);
        message.setData(data);

        String topic = "/" + request.getDeviceId() + "/server2client";
        mqttGateway.sendToMqtt(topic, message);
    }

    /**
     * 获取当前星期几（1=星期一，7=星期日）
     * 根据需要调整获取星期的方法
     */
    private int getCurrentDayOfWeek() {
        java.time.DayOfWeek day = java.time.LocalDate.now().getDayOfWeek();
        return day.getValue();
    }
}
