package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.PropertiesUtil;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.OrderComment;
import com.sell.modules.store.service.FileService;
import com.sell.modules.store.service.OrderCommentService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/3/31 23:24
 */
@RestController
@RequestMapping("comment")
public class OrderCommentController {
    @Autowired
    private OrderCommentService orderCommentService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FileService fileService;
    @PostMapping("save")
    public Res<String> save(OrderComment orderComment, HttpServletRequest request){
        orderComment.setUserId(UserUtils.getUserId());
        if(orderComment.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(orderComment.getFile(),path, Const.FTPPATH_COMMENT);
            if(targetFileName == null){
                return Res.errorMsg("保存评价图片失败");
            }
            String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_COMMENT+"/"+targetFileName;
            orderComment.setImages(url);
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

}
