package com.ruoyi.lock.mapper;

import com.ruoyi.lock.domain.FreedormLock;

import java.util.List;

public interface FreedormLocksMapper {
    int insertFreedormLock(FreedormLock freedormLock);

    List<FreedormLock> selectFreedormLockList(FreedormLock freedormLock);

    FreedormLock selectFreedormLockById(String deviceId);

    int updateFreedormLock(FreedormLock freedormLock);

    int deleteFreedormLockById(String deviceId);

    int deleteFreedormLocksByIds(String[] deviceIds);
}
