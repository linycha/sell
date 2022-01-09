package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/03/01 00:35
 */
@Data
public class OrderComment {
    private Integer id;

    private Long orderNo;

    private Integer userId;
    private Integer shopId;
    private Integer parentId;

    private String content;

    private String images;

    private BigDecimal score;

    private String status;
    private String reply;
    private String isAnonymity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    //用于接收前端传过来的值
    private MultipartFile file;

    private String scores;

    private String username;
}