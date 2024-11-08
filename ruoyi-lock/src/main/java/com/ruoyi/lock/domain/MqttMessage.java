package com.ruoyi.lock.domain;

public class MqttMessage<T> {
    private String Operate;
    private Long Timestamp;
    private T Data;

    // getters and setters

    public String getOperate() {
        return Operate;
    }

    public void setOperate(String operate) {
        Operate = operate;
    }

    public Long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Long timestamp) {
        Timestamp = timestamp;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
