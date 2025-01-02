package com.ruoyi.lock.mapper;

import com.ruoyi.lock.domain.FreedormLockSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreedormLockSchedulesMapper {
    int insertFreedormLockSchedule(FreedormLockSchedule schedule);

    List<FreedormLockSchedule> selectFreedormLockScheduleList(FreedormLockSchedule schedule);

    FreedormLockSchedule selectFreedormLockScheduleById(Long scheduleId);

    int updateFreedormLockSchedule(FreedormLockSchedule schedule);

    int deleteFreedormLockScheduleById(Long scheduleId);

    int deleteFreedormLockSchedulesByIds(Long[] scheduleIds);

    int deleteFreedormLockSchedule(FreedormLockSchedule schedule);

    List<FreedormLockSchedule> findByDeviceId(String deviceId);

    /**
     * 根据设备ID和星期查询时间段
     *
     * @param deviceId   设备ID
     * @param dayOfWeek  星期几（1-7）
     * @return 时间段列表
     */
    List<FreedormLockSchedule> findByDeviceIdAndDayOfWeek(@Param("deviceId") String deviceId, @Param("dayOfWeek") Integer dayOfWeek);
}