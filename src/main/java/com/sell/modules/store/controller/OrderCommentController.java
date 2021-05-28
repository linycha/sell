package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.OrderComment;
import com.sell.modules.store.service.OrderCommentService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/3/31 23:24
 */
@Slf4j
@RestController
@RequestMapping("comment")
@Api(tags = "订单评价相关接口")
public class OrderCommentController {
    @Autowired
    private OrderCommentService orderCommentService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FTPUtil ftpUtil;
    @PostMapping("save")
    @ApiOperation("用户提交保存评价信息")
    public Res<String> save(OrderComment orderComment, HttpServletRequest request){
        orderComment.setUserId(UserUtils.getUser().getId());
        if(orderComment.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            try {
                String fileName = ftpUtil.uploadFile(orderComment.getFile(),path, Const.FTP_PATH_COMMENT);
                String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTP_PATH_COMMENT+"/"+fileName;
                orderComment.setImages(url);
            }catch (Exception e){
                log.info(e.getMessage());
                return Res.errorMsg("上传评价图片出现异常");
            }
        }
        BigDecimal score = new BigDecimal(orderComment.getScores());
        orderComment.setScore(score);
        int result = orderCommentService.save(orderComment);
        if(result == 0){
            return Res.errorMsg("评价失败");
        }
        int r = orderService.updateStatusByOrderNo(orderComment.getOrderNo(), Const.OrderStatus.HAVE_EVALUATION);
        if(r == 0){
            return Res.errorMsg("评价失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderComment.getOrderNo(),Const.OrderStatus.HAVE_EVALUATION);
        if(!b2){
            return Res.errorMsg("评价失败");
        }
        System.out.println("评价成功");
        return Res.successMsg("评价成功");
    }

    /**
     * 商家发起查询自己的订单列表
     * @param shopId
     * @param scoreType
     * @param isAnonymity
     * @param status
     * @param pageNum
     * @return Res
     */
    @GetMapping("list")
    @ApiOperation("商家发起查询自己的订单列表")
    public Res<PageInfo<OrderComment>> list(String shopId, String scoreType, String isAnonymity, String status, String pageNum){
        System.out.println("scoreType:"+scoreType+",isAnonymity:"+isAnonymity+",pageNum:"+pageNum+",status:"+status);
        PageInfo<OrderComment> commentList = orderCommentService.list(shopId,scoreType,isAnonymity,status,pageNum);
        if(commentList.getList().size() == 0){
            return Res.errorMsg("未找到相应的评价信息");
        }
        return Res.success(commentList);
    }
    @PostMapping("reply")
    @ApiOperation("商家回复订单评价")
    public Res<String> reply(String orderId,String reply){
        if(StringUtils.isBlank(reply)){
            return Res.errorMsg("回复内容为空");
        }
        int result = orderCommentService.update(orderId,reply);
        if(result == 1){
            return Res.successMsg("回复成功");
        }
        return Res.errorMsg("回复失败");
    }
}
