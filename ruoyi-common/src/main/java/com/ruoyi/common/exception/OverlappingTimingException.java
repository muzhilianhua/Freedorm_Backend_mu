package com.ruoyi.common.exception;

/**
 * 时间段重叠异常
 *
 * @author ruoyi
 */
public class OverlappingTimingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OverlappingTimingException(String message) {
        super(message);
    }
}
