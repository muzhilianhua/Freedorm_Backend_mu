package com.ruoyi.wx.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信用户对象 wx_user
 * 
 * @author maxing
 * @date 2024-10-23
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String id;

    /** openid */
    @Excel(name = "openid")
    private String openid;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 是否禁用 */
    @Excel(name = "是否禁用")
    private Boolean disabled;

    /** 用户住址 */
    @Excel(name = "用户住址")
    private String location;

    /** 房间号 */
    @Excel(name = "房间号")
    private String roomId;

    /** 最近登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 是否已删除 */
    @Excel(name = "是否已删除")
    private Boolean deleted;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setDisabled(Boolean disabled) 
    {
        this.disabled = disabled;
    }

    public Boolean getDisabled() 
    {
        return disabled;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setRoomId(String roomId) 
    {
        this.roomId = roomId;
    }

    public String getRoomId() 
    {
        return roomId;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }
    public void setDeleted(Boolean deleted) 
    {
        this.deleted = deleted;
    }

    public Boolean getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("disabled", getDisabled())
            .append("location", getLocation())
            .append("roomId", getRoomId())
            .append("lastLoginTime", getLastLoginTime())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
