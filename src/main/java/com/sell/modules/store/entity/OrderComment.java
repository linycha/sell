package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/03/01 00:35
 */
public class OrderComment {
    private String id;

    private String orderId;

    private String userId;
    private String shopId;
    private String parentId;

    private String content;

    private String images;

    private BigDecimal score;

    private String status;
    private String reply;
    private String isAnonymity;
    @JsonFormat(pattern = "yy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    //用于接收前端传过来的值
    private MultipartFile file;

    private String scores;

    private String orderNo;

    public OrderComment(String id, String orderId, String userId, String shopId, String parentId, String content, String images, BigDecimal score, String status, String reply, String isAnonymity, Date createTime) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.parentId = parentId;
        this.content = content;
        this.images = images;
        this.score = score;
        this.status = status;
        this.reply = reply;
        this.isAnonymity = isAnonymity;
        this.createTime = createTime;
    }

    public OrderComment() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(String isAnonymity) {
        this.isAnonymity = isAnonymity;
    }
}