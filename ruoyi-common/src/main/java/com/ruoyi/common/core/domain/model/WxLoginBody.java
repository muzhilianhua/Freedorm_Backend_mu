package com.ruoyi.common.core.domain.model;

public class WxLoginBody {
    /** 小程序appid */
    private String appid;

    /** 微信登录code */
    private String code;

    // Getter 和 Setter 方法
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
