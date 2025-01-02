package com.ruoyi.lock.mapper;

import com.ruoyi.lock.domain.FreedormLockSchedule;

import java.util.List;

public interface FreedormLockSchedulesMapper {
    int insertFreedormLockSchedule(FreedormLockSchedule schedule);

    List<FreedormLockSchedule> selectFreedormLockScheduleList(FreedormLockSchedule schedule);

    FreedormLockSchedule selectFreedormLockScheduleById(Long scheduleId);

    int updateFreedormLockSchedule(FreedormLockSchedule schedule);

    int deleteFreedormLockScheduleById(Long scheduleId);

    int deleteFreedormLockSchedulesByIds(Long[] scheduleIds);

    int deleteFreedormLockSchedule(FreedormLockSchedule schedule);
}