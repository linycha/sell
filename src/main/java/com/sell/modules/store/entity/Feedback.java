package com.sell.modules.store.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linyuc
 * @date 2020/3/12 23:21
 */
public class Feedback implements Serializable {
    private String id;
    private String userId;
    private String content;
    //反馈类别
    private String type;
    private Date createTime;

    public Feedback() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
