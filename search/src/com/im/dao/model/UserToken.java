package com.im.dao.model;

import java.util.Date;

public class UserToken {
    private String id;

    private String userid;

    private Integer type;

    private Date createTime;

    private Integer isPolish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsPolish() {
        return isPolish;
    }

    public void setIsPolish(Integer isPolish) {
        this.isPolish = isPolish;
    }
}