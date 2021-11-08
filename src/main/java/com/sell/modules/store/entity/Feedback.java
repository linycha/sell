package com.sell.modules.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linyuc
 * @date 2020/3/12 23:21
 */
@Data
public class Feedback implements Serializable {
    private static final long serialVersionUID = 6862768841930387646L;

    private String id;
    private String userId;
    private String content;
    /**
     * 反馈类别
     */
    private String type;
    private Date createTime;
}
